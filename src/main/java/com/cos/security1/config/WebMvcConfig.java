package com.cos.security1.config;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{  

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
      MustacheViewResolver resolver = new MustacheViewResolver();
//configureViewResolvers 가 view 페이지의 설정 하게 해준다. 
      resolver.setCharset("UTF-8"); // 인코딩 설정 
      resolver.setContentType("text/html;charset=UTF-8");// 데이터 보내는 페이지 html,utf-8
      resolver.setPrefix("classpath:/templates/"); // 위치 view 
      resolver.setSuffix(".html");// 머스테치 .html 

      registry.viewResolver(resolver); // 등록한다. 
  }
}