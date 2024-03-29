package com.ict.shop.dao;

import org.springframework.web.multipart.MultipartFile;

public class ShopVO {
	private String shop_idx, category, p_num, p_name, p_company, p_image_s, p_image_l, p_content, p_date;
	
	// 계산을 위한 것
	private int p_price, p_saleprice;
	
	// 파일 업로드
	private MultipartFile file_s, file_l;
	
	// 장바구니
	private int quant, totalPrice ;
	
	// 세일가격으로 세일 퍼센트 계산(할인률)
	public int getPercent() {
		// 들어올땐 더블 나갈때는 인트여서 형변환 해준다.
		double per = (p_price - p_saleprice) * 100 / p_price;
		return (int)(per);
	}
	
	// 장바구니에서 수량이 변경되면 총 금액도 변경되어야 한다.
	public void setQuant(int quant) {
		this.quant = quant;
		// 세일 가격 계산
		setTotalPrice(quant * p_saleprice);
	}
	
	
	
	public MultipartFile getFile_s() {
		return file_s;
	}

	public void setFile_s(MultipartFile file_s) {
		this.file_s = file_s;
	}

	public MultipartFile getFile_l() {
		return file_l;
	}

	public void setFile_l(MultipartFile file_l) {
		this.file_l = file_l;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuant() {
		return quant;
	}

	
	 public int getP_price() { return p_price; }

		public void setP_price(int p_price) {
			this.p_price = p_price;
		}

		public int getP_saleprice() {
			return p_saleprice;
		}

		public void setP_saleprice(int p_saleprice) {
			this.p_saleprice = p_saleprice;
		}

	public String getShop_idx() {
		return shop_idx;
	}

	public void setShop_idx(String shop_idx) {
		this.shop_idx = shop_idx;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_company() {
		return p_company;
	}

	public void setP_company(String p_company) {
		this.p_company = p_company;
	}


	public String getP_image_s() {
		return p_image_s;
	}

	public void setP_image_s(String p_image_s) {
		this.p_image_s = p_image_s;
	}

	public String getP_image_l() {
		return p_image_l;
	}

	public void setP_image_l(String p_image_l) {
		this.p_image_l = p_image_l;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	
}
