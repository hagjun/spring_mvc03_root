package com.ict.member.service;

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

}
