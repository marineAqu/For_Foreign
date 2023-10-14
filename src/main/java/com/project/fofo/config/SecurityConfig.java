package com.project.fofo.config;

import com.project.fofo.service.OAuth2MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2MemberService oAuth2MemberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/join").permitAll()
                        .requestMatchers("/css/**", "/images/**", "/javascript/**").permitAll()
                        .anyRequest().authenticated()
                );
        http
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("uid")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login_process")
                        .defaultSuccessUrl("/main", true)
                        .failureForwardUrl("/login"));
        http
                .oauth2Login(login -> login
                        .loginPage("/login") // 구글 로그인이 완료된 뒤의 후처리가 필요
                        .defaultSuccessUrl("/main", true)
                        .failureUrl("/login")
                        .userInfoEndpoint() // 로그인 완료 후 회원 정보 받기
                        .userService(oAuth2MemberService));
        http
                .logout(logout -> logout
                        .logoutSuccessUrl("/login"));
        http
                .csrf().disable(); //CSRF 공격에 대한 방어를 해제

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
