package com.ict.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.dao.MemberDAO;
import com.ict.member.dao.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberdao;
	

	@Override
	public MemberVO getLogin(MemberVO mvo) {
		
		return memberdao.getLogin(mvo);
	}
	
	@Override
	public List<MemberVO> getMemberList() {
		return memberdao.getMemberList();
	}
	
	@Override
	public String getIdChk(String m_id) {
		return memberdao.getIdChk(m_id);
	}
	
	@Override
	public int getAjaxJoin(MemberVO mvo) {
		return memberdao.getAjaxJoin(mvo);
	}

}
