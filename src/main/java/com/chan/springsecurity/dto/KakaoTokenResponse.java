package com.chan.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoTokenResponse(
        @JsonProperty("token_type")
        String tokenType,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        Integer expiresIn,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("refresh_token_expires_in")
        String refreshTokenExpiresIn,
        @JsonProperty("scope")
        String scope
) {
    private static final String FAIL = "fails";

    public static KakaoTokenResponse failResponse() {
        return new KakaoTokenResponse(
                FAIL, FAIL, 0,
                FAIL, FAIL, FAIL);
    }
}
