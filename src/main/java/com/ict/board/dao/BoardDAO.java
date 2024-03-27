package com.ict.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board.count");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	
	public List<BoardVO> getBoardList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			
			
			return sqlSessionTemplate.selectList("board.board_list", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	
	public int getBoardInsert(BoardVO bovo) {
		try {
			return sqlSessionTemplate.insert("board.board_insert", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	
	public int getBoardHit(String bo_idx) {
		try {
			return sqlSessionTemplate.update("board.board_hit", bo_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	
	public BoardVO getBoardDetail(String bo_idx) {
		try {
			return sqlSessionTemplate.selectOne("board.board_detail", bo_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	
	public int getLevelUpdate(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getAnsInsert(BoardVO bovo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
