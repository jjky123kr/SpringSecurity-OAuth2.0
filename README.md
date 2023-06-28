# SpringSecurity-OAuth2.0

### OAuth2.0 라이브러리 사용

### maven 환경설정에  설정한다. 

```xml
 <!-- oauth -->

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-oauth2-client</artifactId>
	</dependency>
```

## 소셜 로그인 (구글 , 페이스북, 네이버, 카카오)

### 로그인 할 때 조건문을 사용하여 getClientRegistration().getRegistrationId() 값이<br> 구글 ,페이스북, 네이버, 카카오에 따라 로직을 구현 한다.


## OAuth2.0 에는 provider 제공한다. 

* provider => 구글, 페이스북, 트위터 지원한다.

#### provider 네이버 카카오는 지원한지 않는다.

* application.yml 에서 provider 를 설정한다.
  <img width="674" alt="n5" src="https://github.com/jjky123kr/SpringSecurity-OAuth2.0/assets/107549149/1cd2736c-2a0d-4ee0-99d8-1828022802ef">

  <img width="527" alt="k6" src="https://github.com/jjky123kr/SpringSecurity-OAuth2.0/assets/107549149/7cbb8b46-f6f6-477e-b869-17baaa64fe37">





