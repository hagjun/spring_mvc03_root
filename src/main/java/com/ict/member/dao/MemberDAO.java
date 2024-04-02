package com.ict.member.dao;

import java.util.List;

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
	
	public List<MemberVO> getMemberList() {
		try {
			return sqlSessionTemplate.selectList("member.ajax_list");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public String getIdChk(String m_id) {
		try {
			int result = sqlSessionTemplate.selectOne("member.idchk", m_id);
			// m_id 가 존재하면
			if(result > 0) {
				return "0";
			}
			// m_id 가 존재하지 않으면
			return "1";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public int getAjaxJoin(MemberVO mvo) {
		try {
			return sqlSessionTemplate.insert("member.join", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
