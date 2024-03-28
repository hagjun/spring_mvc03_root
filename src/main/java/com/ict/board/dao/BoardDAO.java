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
		try {
			return sqlSessionTemplate.update("board.lev_update", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	
	public int getAnsInsert(BoardVO bovo) {
		try {
			return sqlSessionTemplate.insert("board.ans_insert", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getBoardDelete(BoardVO bovo) {
		try {
			if(bovo.getStep().equals("0")) {
				return sqlSessionTemplate.update("board.board_delete", bovo);
				
			}else {
				return sqlSessionTemplate.delete("board.board_ans_delete", bovo);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getBoardUpdate(BoardVO bovo) {
		try {
			return sqlSessionTemplate.update("board.board_update", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}















