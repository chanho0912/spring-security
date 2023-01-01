package com.chan.springsecurity.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * Spring Security가 /login으로 요청이 오면 낚아채서 로그인을 진행
 * @see com.chan.springsecurity.config.SecurityConfig
 *
 * 로그인을 완료하면 Security ContextHolder에 session을 만들어 준다.
 * Object type => Authentication 타입 객체
 * Authentication 객체 안에 User 정보가 있어야 함.
 * Object type => UserDetails
 *
 * Security Session => Authentication => UserDetails
 */
public class PrincipalDetails implements UserDetails, OAuth2User {

    // https://www.youtube.com/watch?v=3iypR-1Glm0
    private final transient Member member;
    private Map<String, Object> attributes = Map.of();

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // 해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) member::getRole);

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public String getName() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        /*
         휴먼 계정 정책에 따라 결정
         */
        return true;
    }

}
