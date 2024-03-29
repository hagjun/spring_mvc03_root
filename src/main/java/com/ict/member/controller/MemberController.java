package com.ict.member.controller;

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
	
	@GetMapping("login.do")
	public ModelAndView getLogin() {
		return new ModelAndView("member/login");
	}
	
	@PostMapping("login_ok.do")
	public ModelAndView getLoginOK(MemberVO mvo) {
		try {
			ModelAndView mv = new ModelAndView();
			
			MemberVO mvo2 = memberService.getLoginOK(mvo);
			if (mvo2.getM_id()!= null) {
				mv.setViewName("shop/shop_list");
				mv.addObject("mvo2", mvo2);
				return mv;
				
			}else {
				mv.setViewName("login.do");
				return mv;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("member/error");
	}
	
}
