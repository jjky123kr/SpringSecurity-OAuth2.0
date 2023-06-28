package com.cos.security1.config.oauth.provider;


//OAuth2.0 제공자들 마다 응답해주는 속성값이 달라서 공통으로 만들어준다.

public interface OAuthUserinfo {

	String getProvider_id();// 구글 , 페이스북, 네이버
	String getProvider();
	String getEmail(); // 소셜의 이메일
	String getName();  // 소셜 회원의 이름
	
	
}
