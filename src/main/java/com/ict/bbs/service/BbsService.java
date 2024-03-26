package com.ict.bbs.service;

import java.util.List;

import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;

public interface BbsService {
	// 리스트
	public List<BbsVO> getBbsList();
	
	// 삽입
	public int getBbsInsert(BbsVO bvo);
	
	// 상세보기
	public BbsVO getBbsDetail(String b_idx);
	
	// 원글 삭제
	public int getBbsDelete(String b_idx);
	
	// 원글 수정
	public int getBbsUpdate(BbsVO bvo);
	
	
	// 조회수 업데이트
	public int getHitUpdate(String b_idx);
	
	// 페이징 처리 - 전체 게시물의 수
	public int getTotalCount();
	
	// 페이징 처리를 위한 리스트 
	public  List<BbsVO> getBbsList(int offset, int limit);
	
	// 댓글 가져오기
	public List<CommentVO> getCommentList(String b_idx);
	
	// 댓글 삽입
	public int getCommentInsert(CommentVO cvo);
	
	// 댓글 삭제
	public int getCommentDelete(String c_idx);
	
}
