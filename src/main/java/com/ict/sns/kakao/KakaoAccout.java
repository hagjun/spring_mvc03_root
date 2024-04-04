package com.ict.sns.kakao;

public class KakaoAccout {
	// 변수값은 json 키값과 동일 해야 된다.
	private String email;

	public KakaoAccout(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
