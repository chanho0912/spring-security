package com.chan.springsecurity.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chan.springsecurity.config.KakaoClient;
import com.chan.springsecurity.dto.KakaoTokenResponse;

@RestController
public class KakaoOauthController {

    private final KakaoClient kakaoClient;

    public KakaoOauthController(KakaoClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    @GetMapping("auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        final ResponseEntity<KakaoTokenResponse> respEntity = kakaoClient.getAccessToken(
                kakaoClient.getTokenRequestSpec(code)
        );

        return Optional.ofNullable(respEntity.getBody())
                       .orElseGet(KakaoTokenResponse::failResponse)
                       .accessToken();
    }

}
