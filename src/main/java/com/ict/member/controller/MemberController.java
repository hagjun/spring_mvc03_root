package com.ict.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.dao.MemberVO;
import com.ict.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("loginForm.do")
	public ModelAndView LoginForm() {
		return new ModelAndView("member/loginForm");
	}
	
	@PostMapping("login_ok.do")
	public ModelAndView getLogin(MemberVO mvo, HttpSession session) {// 로그인 관련된건 세션으로 받아라.
		// 암호화 처리방식을 안함
		/*
		  try { ModelAndView mv = new ModelAndView();
		  
		  MemberVO mvo2 = memberService.getLoginOK(mvo); 
		  if (mvo2.getM_id()!= null) {
		  mv.setViewName("shop/shop_list"); 
		  mv.addObject("mvo2", mvo2); 
		  return mv;
		  
		  }else { 
		  mv.setViewName("login.do"); 
		  return mv; }
		  
		  
		  } catch (Exception e) { 
		  	System.out.println(e); 
		  } 
		  return new
		  ModelAndView("member/error");
		 */
		ModelAndView mv = new ModelAndView();
		MemberVO mvo2 = memberService.getLogin(mvo);
		if(mvo2 == null) {
			session.setAttribute("loginChk", "fail");
			mv.setViewName("member/loginForm");
			return mv;
		}else {
			session.setAttribute("loginChk", "ok");
			// admin 으로 성공시
			if(mvo2.getM_id().equals("admin")) {
				session.setAttribute("admin", "ok");
			}
			session.setAttribute("mvo2", mvo2);
			mv.setViewName("redirect:shop_list.do");
			return mv;
		}
		
	}
	@GetMapping("member_logout.do")
	public ModelAndView getLogOut(HttpSession session) {
		// 세션 전체 초기화
		// session.invalidate();
		// 필요 정보만 삭제
		session.removeAttribute("loginChk");
		session.removeAttribute("admin");
		session.removeAttribute("mvo2");
		return new ModelAndView("redirect:shop_list.do");
		
	}
	
}











