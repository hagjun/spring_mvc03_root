package com.ict.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/*
	 * public MemberVO getLoginOK(MemberVO mvo) throws Exception {
	 * 
	 * return sqlSessionTemplate.selectOne("member.detail", mvo); }
	 */
	
	public MemberVO getLogin(MemberVO mvo) {
		try {
			return sqlSessionTemplate.selectOne("member.login", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
