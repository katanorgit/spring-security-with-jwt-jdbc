package com.handson.demo.util;

public class JWTResponse {
	private String token;
	
	public JWTResponse() {
		// TODO Auto-generated constructor stub
	}

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
