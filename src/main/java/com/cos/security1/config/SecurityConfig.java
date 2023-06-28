package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
//1.코드받기(인증),2.엑세스토큰(권한)
//3.사용자 프로필 정보를 가져옴.4-1.그정보를 토대로 회원가입을 자동으로 진행시킨다. 
//4-2 (이메일, 전화번호,이름,아이디) 쇼핑몰->집주소->백화점 몰
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) // 시큐어 어노테이션 활성화,preAutorize 어노테이션 활성화
public class SecurityConfig{
//SecurityConfig 필터 
	
	@Lazy
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	// password 암호화 
	@Lazy
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	
	public SecurityFilterChain filterCahin(HttpSecurity http)throws Exception{
		http.csrf().disable(); //비활성화
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		.antMatchers("/manger/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_MANGER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()  
		.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/loginProc") // /login 주소가 호출 되면, 시큐리티가 낚아서 자동 로그인
		.defaultSuccessUrl("/")
		.and()
		.oauth2Login()
		.loginPage("/loginForm")
		.userInfoEndpoint()
		.userService(principalOauth2UserService);//구글 로그인 후 후처리가 필요한다.Tip 코드X, (엑세스 토큰 +사용자 정보 프로필); 
		return http.build();
	}
}
