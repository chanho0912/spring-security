package com.chan.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // spring security filter가 spring filter chain에 등록됨
@EnableMethodSecurity
public class SecurityConfig {

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
            .defaultSuccessUrl("/");

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
