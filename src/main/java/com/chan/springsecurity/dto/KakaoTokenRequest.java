package com.chan.springsecurity.dto;

public record KakaoTokenRequest(
        String grantType,
        String clientId,
        String redirectUri,
        String code
) {
    public KakaoTokenRequest(String code) {
        this("authorization_code",
             "3f887320717daeba63a44f87656ecb67",
             "http://localhost:8080/auth/kakao/callback",
             code);
    }
}
