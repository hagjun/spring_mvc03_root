package com.ict.bbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;
import com.ict.bbs.service.BbsService;
import com.ict.board.dao.BoardVO;
import com.ict.common.Paging;

@Controller
public class BbsController {
	@Autowired
	private BbsService bbsService;
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private Paging paging;
	
	// @RequestMapping => get/post방식을 둘다 받을 수 있다.
	@RequestMapping("bbs_list.do")
	public ModelAndView getBbsList(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("bbs/list");
		/*
		List<BbsVO> list = bbsService.getBbsList();
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("bbs/error");
		*/
		// 전체 게시물(행)의 수를 구한다.
		int count = bbsService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수를 구하자.
		if(paging.getTotalRecord()  <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if(paging.getTotalPage() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		
		// 현재 페이지 구하기 => begin, end를 구한다.
		String cPage = request.getParameter("cPage");
		
		// 맨 처음에 들어오면 cPage가 없으므로 null 이다.
		// 맨 처음 오면 무조건 1 페이지 이다.
		if(cPage == null) { // cPage => 페이지번호 눌렀을 때 (첫 페이지 1)
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		// 오라클 begin, end
		// 마리아DB limit, offset
		// offset 공식 = limit * (현재 페이지 -1)
		// limit = numPerPage 
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
		
		/* System.out.println(bbs_list.size()); */
		
		// 시작블록과 끝블록 구하기
		paging.setBeginBlock(
				(int)(((paging.getNowPage() -1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1));
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() -1);

		// 주의사항
		// EndBlock 과 TotalPage 가 다르면 혹은 EndBlock이 크면 EndBlock 을 TotalPage로 지정한다.
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		List<BbsVO> bbs_list = bbsService.getBbsList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("bbs_list", bbs_list);
		mv.addObject("paging", paging);
		
		return mv;
	}
	@GetMapping("bbs_write.do")
	public ModelAndView getBbsWrite() {
		return new ModelAndView("bbs/write");
	}
	
	// 파일 업로드를 해야하기 때문에 HttpServletRequest도 넣어준다.
	@PostMapping("bbs_write_ok.do")
	public ModelAndView getBbsWriteOK(BbsVO bvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:bbs_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = bvo.getFile_name();
			
			if(file.isEmpty()) {// 파일이 비었으면
				bvo.setF_name("");// 디비에 저장하는 이름 f_name
			}else { // 파일이 있으면
				UUID uuid = UUID.randomUUID(); // 이름 중복 X
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();
				bvo.setF_name(f_name);
				
				// 이미지 저장
				byte[] in = file.getBytes();
				File out = new File(path, f_name); 
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			String pwd = passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(pwd);
			
			// 디비에 저장
			int result = bbsService.getBbsInsert(bvo);
			if(result > 0) {
				return mv;
			}
			return new ModelAndView("bbs/error");
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("bbs/error");
	}
	
	@GetMapping("bbs_detail.do") 
	public ModelAndView getBbsDetail(String b_idx, String cPage) {
		ModelAndView mv = new ModelAndView("bbs/detail");
		// 조회수 증가 - 트랜잭션 (한묶음) - 같이 처리한다.
		int result = bbsService.getHitUpdate(b_idx);
		
		// 상세보기 - 트랜잭션 (한묶음) - 같이 처리한다.
		BbsVO bvo = bbsService.getBbsDetail(b_idx);
		
		if(result > 0 && bvo != null) { // 둘다 성공하면
			// 댓글 가져오기
			List<CommentVO> comm_list = bbsService.getCommentList(b_idx); // b_idx => 그글에 관련된 댓글만 가져온다.
			mv.addObject("comm_list", comm_list);
			mv.addObject("bvo", bvo);
			mv.addObject("cPage", cPage);
			return mv; // 둘다 성공했을때 커밋 / 롤백 한다.
		}
		
		
		return new ModelAndView("bbs/error");
	}
	
	@PostMapping("comment_insert.do")
	 public ModelAndView getCommentInsert(CommentVO cvo, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentInsert(cvo);
		return mv;
	}
	@PostMapping("comment_delete.do")
	public ModelAndView getCommentDelete(String c_idx, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentDelete(c_idx);
		return mv;
	}
	
	@PostMapping("bbs_delete.do")
	public ModelAndView getBbsDelete(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("b_idx")String b_idx) {
		return new ModelAndView("bbs/delete");
	}
	@PostMapping("bbs_delete_ok.do")
	public ModelAndView getBbsDeleteOK(@RequestParam("pwd")String pwd, 
			@ModelAttribute("cPage")String cPage, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView();
		
		// 비밀번호 체크
		BbsVO bvo = bbsService.getBbsDetail(b_idx);
		String dpwd = bvo.getPwd();
		
		if(passwordEncoder.matches(pwd, dpwd)) {
			mv.setViewName("bbs/delete");
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			// 원글 삭제 (그냥 삭제하면 외래키 때문에 오류 발생)
			// active 컬럼의 값을 1로 변경하자.
			int result = bbsService.getBbsDelete(b_idx);
			if(result > 0) {
				mv.setViewName("redirect:bbs_list.do");
				return mv;
			}
			
		}
		return new ModelAndView("bbs/error");
	}
	
}
	
	
	
	
	

