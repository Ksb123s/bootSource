package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // 웹에서 security 적용 가능하게 하는 기능
@Configuration // @component(@controller, @service) : 객체 생성
public class SecurityConfig {

    // 접근 제한 개념

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // 요청 확인
                .requestMatchers("/", "/security/guest", "/auth").permitAll()
                .requestMatchers("/security/member").hasRole("USER")
                .requestMatchers("/security/admin").hasRole("ADMIN"))
                // 인증 처리( 웹에서는 대부분 폼 로그인 작업)
                // .formLogin(Customizer.withDefaults());// default 로그인 페이지 보여주기
                .formLogin(login -> login
                .loginPage("/member/login").permitAll()// custom 로그인 이용
                // .usernameParameter("userid") //username 요소 이름 변경
                // .passwordParameter("pwd") password 요소 이름 변경
                // .successHandler(null)) 로그인 성공 후 가야할 곳 지정
                )
                // custom logout
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/"));// default  페이지 이동
                                                                                                            

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // InMemoryUserDetailsManager 임시 사용 메모리에 등록
    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder().username("user1")
                .password("{bcrypt}$2a$10$34RZQNHzBAY5t15lsqYTd.ux25ht309cgj8EEu84Srklni1REief2").roles("USER").build();
        UserDetails admin = User.builder().username("admin1")
                .password("{bcrypt}$2a$10$34RZQNHzBAY5t15lsqYTd.ux25ht309cgj8EEu84Srklni1REief2").roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
