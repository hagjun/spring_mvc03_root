package com.ict.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook.dao.GuestBookDAO;
import com.ict.guestbook.dao.GuestBookVO;


@Service
public class GuestBookServiceImpl implements GuestBookService{
	@Autowired
	private GuestBookDAO guestBookDAO;
	
	@Override
	public List<GuestBookVO> getGuestList() {
		return guestBookDAO.getGuestList();
	}

	@Override
	public int getGuestInsert(GuestBookVO gvo) {
		return guestBookDAO.getGuestInsert(gvo);
	}

	@Override
	public GuestBookVO getGuestDetail(String idx) {
		return guestBookDAO.getGuestDetail(idx);
	}

	@Override
	public int getGuestDelete(String idx) {
		return guestBookDAO.getGuestDelete(idx);
	}

	@Override
	public int getGuestUpdate(GuestBookVO gvo) {
		return guestBookDAO.getGuestUpdate(gvo);
	}
	
}
