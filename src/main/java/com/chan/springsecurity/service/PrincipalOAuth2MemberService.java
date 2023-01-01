package com.chan.springsecurity.service;

import java.util.Map;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.chan.springsecurity.model.Member;
import com.chan.springsecurity.model.PrincipalDetails;
import com.chan.springsecurity.repository.MemberRepository;

@Service
public class PrincipalOAuth2MemberService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public PrincipalOAuth2MemberService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                        MemberRepository memberRepository) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(userRequest);
        final Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println("attributes: " + attributes);

        final String provider = userRequest.getClientRegistration().getRegistrationId();
        final String providerId = oAuth2User.getAttribute("sub");
        final String username = provider + "_" + providerId;
        // TODO: password를 가변적으로 넣는 방법이 추가되어야 함
        final String password = bCryptPasswordEncoder.encode("default_oauth");
        final String email = oAuth2User.getAttribute("email");
        final String role = "ROLE_USER";

        Member memberEntity = memberRepository.findByUsername(username);

        if (Objects.isNull(memberEntity)) {
            memberEntity = Member.builder()
                                 .username(username)
                                 .password(password)
                                 .role(role)
                                 .authProvider(provider)
                                 .authProviderId(providerId)
                                 .build();
            memberRepository.save(memberEntity);
        }

        return new PrincipalDetails(memberEntity, attributes);
    }

    /*
     username = google_${sub}
     password = "encrypted(password)"
     email = returned email
     role = ROLE_USER
     */
}
