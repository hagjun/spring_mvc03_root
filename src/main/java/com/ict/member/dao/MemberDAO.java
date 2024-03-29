package com.ict.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MemberVO getLoginOK(MemberVO mvo) throws Exception {
		
		return sqlSessionTemplate.selectOne("member.detail", mvo);
	}
}
