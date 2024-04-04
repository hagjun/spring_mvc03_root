package com.ict.sns.kakao;


public class KakaoUserVO {
	private String id, connected_at;
	private KakaoProperties properties;
	private KakaoAccout kakao_account;
	
	public KakaoUserVO(String id, String connected_at, KakaoProperties properties, KakaoAccout kakao_account) {
		this.id = id;
		this.connected_at = connected_at;
		this.properties = properties;
		this.kakao_account = kakao_account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public KakaoProperties getProperties() {
		return properties;
	}

	public void setProperties(KakaoProperties properties) {
		this.properties = properties;
	}

	public KakaoAccout getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(KakaoAccout kakao_account) {
		this.kakao_account = kakao_account;
	}
	
	
}
