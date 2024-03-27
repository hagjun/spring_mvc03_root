package com.ict.common;

import org.springframework.stereotype.Component;

// 서비스, 레포지토리, 컨트롤러 위에 있는 애(객체생성할때)
@Component
public class Paging {
	private int nowPage = 1;  // 첫 화면 들어갔을 때 첫 페이지
	private int nowBlock = 1; // 앞으로 가거나 뒤로 가는 버튼사이에 있는 페이지 블럭
	
	private int numPerPage = 5; // 한 페이지당 10개 들어간다.
	private int pagePerBlock = 3; // 한 블록당 3개가 들어간다.
	
	private int totalRecord = 0; // DB의 게시물의 수 
	private int totalPage = 0;   // 게시물의 수를 이용해서 전체 페이지의 수
	private int totalBlock = 0;  // 전체 블록의 수
	
	// 가장 중요
	private int begin = 0; // 한번에 가져올 게시물의 시작번호
	private int end = 0;   // 한번에 가져올 게시물의 끝번호
	
	private int beginBlock = 0; // 시작 블록
	private int endBlock = 0;   // 끝 블록
	
	private int offset = 0;

	
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getBeginBlock() {
		return beginBlock;
	}

	public void setBeginBlock(int beginBlock) {
		this.beginBlock = beginBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
}
