package com.chan.springsecurity.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOAuth2MemberService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("user request: " + super.loadUser(userRequest).getAttributes());
        return super.loadUser(userRequest);
    }

    /*
     username = google_${sub}
     password = "encrypted(password)"
     email = returned email
     role = ROLE_USER
     */
}
