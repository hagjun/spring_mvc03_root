package com.ict.sns.naver;

public class NaverResponse {
	private String id, nickname, email, mobile, name, profile_image;

	public NaverResponse(String id, String nickname, String email, String mobile, String name, String profile_image) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
		this.profile_image = profile_image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	
	
	
}
