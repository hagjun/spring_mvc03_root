package com.ict.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.dao.BoardVO;
import com.ict.board.service.BoardService;
import com.ict.common.Paging;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping("board_list.do")
	public ModelAndView boardList(HttpServletRequest request ) {
		ModelAndView mv = new ModelAndView("board/board_list");
		
		// 페이징 기법
		// 전체 게시물의 수
		int count = boardService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1); 
			}
		}
		
		// 현재 페이지 구하기
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// begin, end 구하기 (오라클)
		// offset 구하기
		// offset = limit * (현재페이지-1);
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));
		
		// 시작 블록 // 끝블록
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() -1 );
		
		// 주의사항
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		// 디비
		List<BoardVO> board_list = boardService.getBoardList(paging.getOffset(), paging.getNumPerPage());
		if(board_list != null) {
			mv.addObject("board_list", board_list);
			mv.addObject("paging", paging);
			return  mv;
		}
		
 		return new ModelAndView("board/error");
	}
	
	@GetMapping("board_write.do")
	public ModelAndView getBoardWrite() {
		return new ModelAndView("board/write");
		
	}
	@PostMapping("board_write_ok.do")
	public ModelAndView getBoardWriteOK(BoardVO bovo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:board_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = bovo.getFile();
			if(file.isEmpty()) {
				bovo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid+ "_" + file.getOriginalFilename();
				bovo.setF_name(f_name);
				
				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			bovo.setPwd(passwordEncoder.encode(bovo.getPwd()));
			
			int result = boardService.getBoardInsert(bovo);
			if(result>0) {
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("board/error");
	}
	@GetMapping("board_detail.do")
	public ModelAndView getBoardDetail(@ModelAttribute("cPage")String cPage, String bo_idx) {
		ModelAndView mv = new ModelAndView("board/detail");
		// hit 업데이트
		int result = boardService.getBoardHit(bo_idx);
		
		// 상세보기
		BoardVO bovo = boardService.getBoardDetail(bo_idx);
		
		if(result > 0 && bovo != null) {
			mv.addObject("bovo", bovo);
			return mv;
		}
		return new ModelAndView("board/error");
		
	}
	@PostMapping("ans_write.do")
	public ModelAndView getBoardAnsWrite(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("bo_idx")String bo_idx) {
		
		return new ModelAndView("board/ans_write");
	}
	
	@PostMapping("board_ans_write_ok.do")
	public ModelAndView getBoardAnsWriteOK(@ModelAttribute("cPage")String cPage,
			BoardVO bovo, HttpServletRequest request) {
			
		try {
			// 답글에서만 처리 할일
			// 원글의 groups, step, lev를 가져와라
			BoardVO bovo2 = boardService.getBoardDetail(bovo.getBo_idx());
			
			int groups = Integer.parseInt(bovo2.getGroups()); // 원본그룹
			int step = Integer.parseInt(bovo2.getStep());
			int lev = Integer.parseInt(bovo2.getLev()); 
			
			// step, lev을 하나씩 올리자
			step++;
			lev++;
			
			// DB에서 lev 를 업데이트 하자
			// ** groups이 같은 글을 찾아서 기존데이터의 lev 이 같거나 크면 기존 데이터의 lev 을 증가
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int result = boardService.getLevelUpdate(map);
			bovo.setGroups(String.valueOf(groups));
			bovo.setStep(String.valueOf(step));
			bovo.setLev(String.valueOf(lev));
			///////////// 댓글을 달면서 화면서 정렬할 수 있음 ///////////
			
			ModelAndView mv = new ModelAndView("redirect:board_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = bovo.getFile();
			if(file.isEmpty()) {
				bovo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				bovo.setF_name(f_name);
				
				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			bovo.setPwd(passwordEncoder.encode(bovo.getPwd()));
			
			/* int result2 = boardService.getBoardInsert(bovo); */
			int result2 = boardService.getAnsInsert(bovo); 
			
			if(result2>0) {
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("board/error");
	}
	@PostMapping("board_delete.do")
	public ModelAndView getBoardDelete(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("bo_idx")String bo_idx) {
		
		return new ModelAndView("board/delete");
	}
	@PostMapping("board_delete_ok.do")
	public ModelAndView getBoardDeleteOK(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("bo_idx") String bo_idx,
			BoardVO bovo) {
		ModelAndView mv = new ModelAndView();
		
		BoardVO bovo2 = boardService.getBoardDetail(bovo.getBo_idx());
		String dpwd = bovo2.getPwd();
		
		if(!passwordEncoder.matches(bovo.getPwd(), dpwd)) {
			mv.setViewName("board/delete");
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			// 원글 삭제 (그냥 삭제하면 외래키 때문에 오류 발생)
			// active 컬럼의 값을 1로 변경하자.
			int result = boardService.getBoardDelete(bovo2);
			if(result > 0) {
				mv.setViewName("redirect:board_list.do");
				return mv;
			}
			
		}
		return new ModelAndView("board/error");
		
	}
	@PostMapping("board_update.do")
	public ModelAndView getBoardUpdate(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("bo_idx")String bo_idx) {
		ModelAndView mv = new ModelAndView("board/update");
		BoardVO bovo = boardService.getBoardDetail(bo_idx);
		if(bovo != null) {
			mv.addObject("bovo", bovo);
			return mv;
		}
		
		return new ModelAndView("board/error");
	}
	
	@PostMapping("board_update_ok.do")
	public ModelAndView getBoardUpdateOK(
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("bo_idx") String bo_idx,
			BoardVO bovo, HttpServletRequest request) {// 파일첨부
		ModelAndView mv = new ModelAndView();
		
		BoardVO bovo2 = boardService.getBoardDetail(bovo.getBo_idx());
		String dpwd = bovo2.getPwd();
		
		if(! passwordEncoder.matches(bovo.getPwd(), dpwd)) {
			mv.setViewName("board/update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("bovo2", bovo);
			return mv;
		}else {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = bovo.getFile();
				if(file.isEmpty()) {// 파일이 비어있으면
					bovo.setF_name(bovo.getOld_f_name());
				}else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString()+"_"+file.getOriginalFilename();
					bovo.setF_name(f_name);
					/////////  DB에 들어갈거
					
					byte[] in = file.getBytes();
					File out = new File(path, f_name);
					FileCopyUtils.copy(in, out);
				}
				int result = boardService.getBoardUpdate(bovo);
				if(result > 0 ) {
					mv.setViewName("redirect:board_detail.do");
					return mv;
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return new ModelAndView("board/error");
	}
}













