package com.chan.springsecurity.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chan.springsecurity.external.KakaoPublicApiClient;
import com.chan.springsecurity.external.KakaoOAuthClient;
import com.chan.springsecurity.dto.KakaoMemberInfo;
import com.chan.springsecurity.dto.KakaoTokenResponse;

@RestController
public class KakaoOAuthController {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final KakaoPublicApiClient kakaoPublicApiClient;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public KakaoOAuthController(KakaoPublicApiClient kakaoPublicApiClient,
                                KakaoOAuthClient kakaoOAuthClient,
                                BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.kakaoPublicApiClient = kakaoPublicApiClient;
        this.kakaoOAuthClient = kakaoOAuthClient;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        final ResponseEntity<KakaoTokenResponse> respEntity = kakaoOAuthClient.getAccessToken(
                kakaoOAuthClient.getTokenRequestSpec(code)
        );

        try {
            KakaoMemberInfo kakaoMemberInfo = kakaoPublicApiClient.getKakaoMemberInfo(
                    "Bearer " + respEntity.getBody().accessToken());

            System.out.println(kakaoMemberInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.ofNullable(respEntity.getBody())
                       .orElseGet(KakaoTokenResponse::failResponse)
                       .accessToken();
    }

}
