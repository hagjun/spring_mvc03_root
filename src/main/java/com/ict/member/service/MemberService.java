package com.ict.member.service;

import java.util.List;

import com.ict.member.dao.MemberVO;



public interface MemberService {
	// 로그인
	public MemberVO getLogin(MemberVO mvo);
	
	// 전체보기
	public List<MemberVO> getMemberList();
	
	// idChk
	public String getIdChk(String m_id);
	
	// 회원가입
	public int getAjaxJoin(MemberVO mvo);
	
}
