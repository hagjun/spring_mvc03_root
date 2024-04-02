package com.ict.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {
	@GetMapping("spring_ajax_go.do")
	public ModelAndView getStringAjax() {
		return new ModelAndView("ajax/ajax_exam");
	}

	@GetMapping("spring_ajax_go2.do")
	public ModelAndView getStringAjax2() {
		return new ModelAndView("ajax/ajax_exam2");
	}
}
