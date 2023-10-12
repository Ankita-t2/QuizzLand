package com.exam.model;

public class JwtRequest {
	String username;
	String password;
	public JwtRequest(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	public JwtRequest() {
		
	}
	public String getUserName() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
