package com.chan.springsecurity.config;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;
import com.chan.springsecurity.dto.KakaoTokenResponse;

public interface KakaoClient {

    @PostExchange(
            value = "oauth/token",
            contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    ResponseEntity<KakaoTokenResponse> getAccessToken(@RequestParam MultiValueMap<String, String> request);

    default MultiValueMap<String, String> getTokenRequestSpec(String code) {
        final MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        requestParams.add("grant_type", "authorization_code");
        requestParams.add("client_id", "3f887320717daeba63a44f87656ecb67");
        requestParams.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        requestParams.add("code", code);

        return requestParams;
    }
}
