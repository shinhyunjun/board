package com.example.mb.config;

import com.example.mb.common.security.CustomAccessDeniedHandler;
import com.example.mb.common.security.CustomLoginSuccessHandler;
import lombok.extern.java.Log;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

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


        // 스프링 시큐리티가 원하는 결과를 반환하는 쿼리를 작성한다.
        String query1 = "SELECT user_id , user_pw , enabled FROM member2 WHERE user_id = ?";
        String query2 ="SELECT b.user_id, a.auth FROM member_auth a, member2 b WHERE a.user_no = b.user_no AND b.user_id = ?";




        auth.jdbcAuthentication()
                .dataSource(dataSource)

                .usersByUsernameQuery(query1)
                .authoritiesByUsernameQuery(query2)   //작성한 쿼리를 지정한다.

                .passwordEncoder(createPasswordEncoder()); //비밀번호 암호와 처리기를 지정한다.
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


    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
