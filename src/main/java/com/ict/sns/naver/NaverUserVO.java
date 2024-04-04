package com.ict.sns.naver;

public class NaverUserVO {
	private String resultcode, message;
	private NaverResponse response;
	
	
	public NaverUserVO(String resultcode, String message, NaverResponse response) {
		this.resultcode = resultcode;
		this.message = message;
		this.response = response;
	}


	public String getResultcode() {
		return resultcode;
	}


	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public NaverResponse getResponse() {
		return response;
	}


	public void setResponse(NaverResponse response) {
		this.response = response;
	}
	
	
	
	
}
