package com.ict.ajax;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.member.dao.MemberVO;
import com.ict.member.service.MemberService;

@RestController
public class AjaxDBController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "getAjaxList.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String getAjaxList() {
		
		
		//  DB 처리 (전체 정보 가져오기)
		List<MemberVO> list = memberService.getMemberList();
		
		if(list != null) {
			// xml 만들기 
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // => 1. xml 표시
			sb.append("<members>"); // => 2. root 태그
			for (MemberVO k : list) { // => 한명의 정보가(한줄씩 정보가) 돈다.
				sb.append("<member>");
				sb.append("<m_idx>" + k.getM_idx() + "</m_idx>");
				sb.append("<m_id>" + k.getM_id() + "</m_id>");
				sb.append("<m_pw>" + k.getM_pw() + "</m_pw>");
				sb.append("<m_name>" + k.getM_name() + "</m_name>");
				sb.append("<m_age>" + k.getM_age() + "</m_age>");
				sb.append("<m_reg>" + k.getM_reg().substring(0,10) + "</m_reg>");
				sb.append("</member>");
			}
			
			
			sb.append("</members>");
			return sb.toString();
			
		}
		return "fail";
		
	}

	@RequestMapping(value = "getAjaxList2.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAjaxList2() {
		
		//  DB 처리 (전체 정보 가져오기)
		List<MemberVO> list = memberService.getMemberList();
		if(list != null) {
			// pom.xml 에서 gson 추가하기
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		}
		
		return "fail";
		
	}
	
	@RequestMapping(value = "getAjaxIdChk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxIdChk(String m_id) {
		String result = memberService.getIdChk(m_id);
		return result;
		
	}
	
	@RequestMapping(value = "getAjaxJoin.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxJoin(MemberVO mvo) {
		int result = memberService.getAjaxJoin(mvo);
		return String.valueOf(result);
		
	}
}
