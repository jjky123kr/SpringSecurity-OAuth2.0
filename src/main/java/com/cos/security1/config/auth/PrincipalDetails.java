package com.cos.security1.config.auth;
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어 준다.(Security ContextHolder) 
// 시큐리티가 가지고 있는 session에 오브젝트가 정해져 있다. 
// 오브젝트 => Authentication 타입 객체 
//  Authentication 안에 User 정보가 있어야 한다. 
// User 오브젝트 타입=> UserDetails 타입 객체 

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.security1.Model.User;

import lombok.Data;
@Data
// Security Session => Authentication 객체 정해짐 => UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails,OAuth2User {

	private User user;
	
	private Map<String , Object>attributes;
	
	// 일반로그인 생성자
	public PrincipalDetails(User user) {
		this.user=user;
	}
	
	// OAuth 로그인 생성자
	public PrincipalDetails(User user,Map<String, Object>attributes){
		
		this.user=user;
		this.attributes=attributes;
	}
	
	
	// 해당 User 의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection<GrantedAuthority>collect = new ArrayList<>();
	collect.add(new GrantedAuthority() {
		
		@Override
		public String getAuthority() {
			return user.getRole();
		}
	});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 1년동안 회원이 로그인을 안하면 !! 휴먼계정으로 하기
		// 현재 시간 - 로그인 시간 => 1년을 초과 하면 false한다.
		
		return true;
	}

	//OAuth2User 오버라이딩

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
