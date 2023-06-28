package com.cos.security1.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuthUserinfo{

	

	// 생성자 주입
	private Map<String, Object>attibutes;  //oauth2User.getAttribute();
	
	public KakaoUserInfo(Map<String, Object>attributes) {
		this.attibutes = attributes;
	}



	@Override
	public String getProvider_id() {
		// TODO Auto-generated method stub
		return (String)attibutes.get("id").toString();
	}

	@Override
	public String getProvider() {
	
		return "kakao";
	}

	@Override
	public String getEmail() {
		 // kakao_account라는 Map에서 추출
		 return (String) ((Map) attibutes.get("kakao_account")).get("email");
	}

	@Override
	public String getName() {
		 // properties 라는 Map에서 추출
		return (String)((Map)attibutes.get("properties")).get("nickname");
	}


}
