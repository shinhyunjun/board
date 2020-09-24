package com.example.mb.config;

import com.example.mb.common.security.CustomAccessDeniedHandler;
import com.example.mb.common.security.CustomLoginSuccessHandler;
import lombok.extern.java.Log;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config");


        //uri 패턴으로 접근 제한을 설정
        http.authorizeRequests().antMatchers("/board/list").permitAll();

        http.authorizeRequests().antMatchers("/board/register").hasRole("MEMBER");

        http.authorizeRequests().antMatchers("/notice/list").permitAll();

        http.authorizeRequests().antMatchers("/notice/register").hasRole("ADMIN");


        //사용자가 정의한 로그인 페이지의 uri를 지정한다.
        http.formLogin().loginPage("/login").successHandler(createAuthenticationSuccessHandler());

        //로그아웃 처리를 위한 uri를 지정하고, 로그아웃한 후에 세션을 무효화
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);

        //등록한 customAccessDeniedHandler를 접근 거부 처리자로 지정
        http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        //지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.(밑에 두 username이 같으면 안됨)
        auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER"); //회원

        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");  //관리자

    }



    //CustomAccessDeniedHandler를 빈으로 등록한다.
    @Bean
    public AccessDeniedHandler createAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }


    //CustomLoginSuccessHandler를 빈으로 등록한다.
    @Bean
    public AuthenticationSuccessHandler createAuthenticationSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

}
