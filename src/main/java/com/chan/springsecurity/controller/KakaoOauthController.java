package com.chan.springsecurity.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chan.springsecurity.config.KakaoApiClient;
import com.chan.springsecurity.config.KakaoAuthClient;
import com.chan.springsecurity.dto.KakaoMemberInfo;
import com.chan.springsecurity.dto.KakaoTokenResponse;

@RestController
public class KakaoOauthController {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public KakaoOauthController(KakaoApiClient kakaoApiClient,
                                KakaoAuthClient kakaoAuthClient,
                                BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.kakaoApiClient = kakaoApiClient;
        this.kakaoAuthClient = kakaoAuthClient;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        final ResponseEntity<KakaoTokenResponse> respEntity = kakaoAuthClient.getAccessToken(
                kakaoAuthClient.getTokenRequestSpec(code)
        );

        try {
            KakaoMemberInfo kakaoMemberInfo = kakaoApiClient.getKakaoMemberInfo(
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
