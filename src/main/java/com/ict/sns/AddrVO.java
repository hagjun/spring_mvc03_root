package com.ict.sns;

public class AddrVO {
	private String md_idx, member_idx,  postcode, address, detailAddress, extraAddress, basic_addr, memo ;

	public String getPostcode() {
		return postcode;
	}

	public String getMd_idx() {
		return md_idx;
	}

	public void setMd_idx(String md_idx) {
		this.md_idx = md_idx;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public String getBasic_addr() {
		return basic_addr;
	}

	public void setBasic_addr(String basic_addr) {
		this.basic_addr = basic_addr;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	
	
}
