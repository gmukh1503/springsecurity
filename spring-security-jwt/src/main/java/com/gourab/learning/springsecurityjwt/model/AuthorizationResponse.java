package com.gourab.learning.springsecurityjwt.model;

public class AuthorizationResponse {

	private final String jwt;

	
	public AuthorizationResponse(String jwt) {
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}
	
	
	
}
