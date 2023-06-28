package com.cos.security1.config.oauth;


import java.util.Map;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.Model.User;
import com.cos.security1.Repository.UserRepository;
import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.FacebookUserInfo;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.config.oauth.provider.KakaoUserInfo;
import com.cos.security1.config.oauth.provider.NaverUserInfo;
import com.cos.security1.config.oauth.provider.OAuthUserinfo;

// 구글의 사용자 정보 
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired

	private UserRepository userRepository;

	@Autowired
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
// 구글로 부터 받은 userRequest 데이터에 대한 loadUser함수를 사용하여 후 처리한다. 
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("ClientRegistration:" + userRequest.getClientRegistration());// Registrationid로 어떤 OAuth 로
																						// 로그인 했는지 확인가능
		System.out.println("getAccessToken:" + userRequest.getAccessToken());
		OAuth2User oauth2User = super.loadUser(userRequest);
		

		// 구글로그인 버튼 클릭-> 구글 로그인 창 -> 로그인 완료-> 코드리턴(OAuth-Client 라이브) ->AccessToken 요청
		// UserRequest 정보 -> loadUser함수 호출 -> 구글로 부터 회원프로필받는다.
		System.out.println("getAttributes:" + super.loadUser(userRequest).getAttributes());

		OAuthUserinfo oAuthUserinfo = null;
		
	

		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuthUserinfo = new GoogleUserInfo(oauth2User.getAttributes());
			
		}else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuthUserinfo = new FacebookUserInfo(oauth2User.getAttributes());

		}else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuthUserinfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));

		}else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오 로그인 요청");
			oAuthUserinfo = new KakaoUserInfo( oauth2User.getAttributes());
		    
		

		}else{
			System.out.println("우리는 구글과 페이스북만 지원해요 ㅎㅎ");
		}

		
		String provider = oAuthUserinfo.getProvider();
		String provider_id = oAuthUserinfo.getProvider_id();	
		String username = provider + "_" + provider_id;
		String name = oAuthUserinfo.getName();
		String password = bCryptPasswordEncoder.encode("겟인데어"); 
		String email = oAuthUserinfo.getEmail();
		String role = "ROLE_USER";

		User userEntity = userRepository.findByUsername(username);

		// userEntity 가 없으면 강제로 로그인 진행
		
		if (userEntity == null) { 
			System.out.println("로그인이 처음입니다.");
			userEntity = User.builder()
					.username(username)
					.password(password)
					.name(name)
					.eamil(email)
					.role(role)
					.provider(provider)  
					.provider_id(provider_id).build();
			userRepository.save(userEntity);
		} else {
			System.out.println("로그인을 하여,자동으로 회원가입이 되었습니다.");
		}

		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
	
	

}
