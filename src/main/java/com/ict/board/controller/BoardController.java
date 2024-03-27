package com.ict.board.controller;

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
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
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
}













