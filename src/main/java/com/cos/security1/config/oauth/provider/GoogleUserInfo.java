package com.cos.security1.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuthUserinfo{

	// 생성자 주입
	private Map<String, Object>attibutes;  //oauth2User.getAttribute();
	
	public GoogleUserInfo(Map<String, Object>attributes) {
		this.attibutes = attributes;
	}
	
	
	@Override
	public String getProvider_id() {
	
		return (String)attibutes.get("sub");
	}

	@Override
	public String getProvider() {
		return "google";
	}

	@Override
	public String getEmail() {
		
		return (String)attibutes.get("email");
	}

	@Override
	public String getName() {
		
		return (String) attibutes.get("name");
	}

}
