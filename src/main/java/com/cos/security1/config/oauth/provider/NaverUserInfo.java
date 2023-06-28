package com.cos.security1.config.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuthUserinfo {

	// 생성자 주입
	private Map<String, Object> attibutes; // oauth2User.getAttribute();

	public NaverUserInfo(Map<String, Object> attributes) {
		this.attibutes = attributes;
	}

	@Override
	public String getProvider_id() {

		return (String) attibutes.get("id");
	}

	@Override
	public String getProvider() {
		return "naver";
	}

	@Override
	public String getEmail() {

		return (String) attibutes.get("email");
	}

	@Override
	public String getName() {

		return (String) attibutes.get("name");
	}


}
