package com.cos.security1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.Model.User;
import com.cos.security1.Repository.UserRepository;
import com.cos.security1.config.auth.PrincipalDetails;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/test/login")
	public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
		System.out.println("/test/login===================");
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println("authentication:"+principalDetails.getUser());
		
		System.out.println("userDetails"+userDetails.getUser());
		return "세션 정보 확인";
	}
	
	
	@GetMapping("/test/oAuth/login")
	public @ResponseBody String testOAuthLogin(Authentication authentication , @AuthenticationPrincipal OAuth2User oauth) {
		System.out.println("/test/oauth/login===================");
		OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
		
		System.out.println("authentication:"+ oauth2User.getAttributes());
		System.out.println("OAuth2User"+oauth.getAttributes());
		return "세션 정보 확인";
	}
	
	
	
	
	@GetMapping({"","/"})
	public String index() {
		return"index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		System.out.println("PrincipalDetails:"+principalDetails.getUser());
		return"user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return"admin";
	}
	
	@GetMapping("/manger")
	public @ResponseBody String manger() {
		return"manger";
	}
	
	@GetMapping("/loginForm")
	public String login() {
		return"loginForm";
	}
	
	@GetMapping("/joinForm")
	public String join() {
		return"joinForm";
	}
	
	@PostMapping("/join")
	public  String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encoedPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encoedPassword);
		userRepository.save(user);
		return"redirect:/loginForm";
	}
	
	@Secured("ROLE_ADMIM")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return"개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANGER')or hasRole('ROLE_ADMIN')")
	@GetMapping("/data")
	public @ResponseBody String data() {
		return"데이터 정보";
	}
	
	
	
	
}
