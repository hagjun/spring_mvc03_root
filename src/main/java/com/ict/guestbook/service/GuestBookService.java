package com.ict.guestbook.service;

import java.util.List;

import com.ict.guestbook.dao.GuestBookVO;

public interface GuestBookService {
	// 리스트
	public List<GuestBookVO> getGuestList();

	// 삽입
	public int getGuestInsert(GuestBookVO gvo);

	// 상세보기
	public GuestBookVO getGuestDetail(String idx);

	// 삭제하기
	public int getGuestDelete(String idx);

	// 수정하기
	public int getGuestUpdate(GuestBookVO gvo);
}
