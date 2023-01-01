package com.chan.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.chan.springsecurity.service.PrincipalOAuth2MemberService;

@Configuration
@EnableWebSecurity // spring security filter가 spring filter chain에 등록됨
@EnableMethodSecurity
public class SecurityConfig {

    private final PrincipalOAuth2MemberService principalOAuth2MemberService;

    public SecurityConfig(PrincipalOAuth2MemberService principalOAuth2MemberService) {
        this.principalOAuth2MemberService = principalOAuth2MemberService;
    }

    static class UrlPatterns {
        private UrlPatterns() {}

        private static final String USER = "/user/**";
        private static final String MANAGER = "/manager/**";
        private static final String ADMIN = "/admin/**";
    }

    static class Roles {
        private Roles() {}

        private static final String ROLE_MANAGER = "MANAGER";
        private static final String ROLE_ADMIN = "ADMIN";
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(UrlPatterns.USER).authenticated()
            .requestMatchers(UrlPatterns.MANAGER).hasAnyRole(Roles.ROLE_MANAGER, Roles.ROLE_ADMIN)
            .requestMatchers(UrlPatterns.ADMIN).hasRole(Roles.ROLE_ADMIN)
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login-form")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .loginPage("/login-form")
            .userInfoEndpoint()
            .userService(principalOAuth2MemberService);

        return http.build();
    }

}
