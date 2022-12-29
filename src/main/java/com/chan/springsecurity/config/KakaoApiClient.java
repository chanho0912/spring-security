package com.chan.springsecurity.config;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import com.chan.springsecurity.dto.KakaoMemberInfo;

public interface KakaoApiClient {

    @GetExchange("v2/user/me")
    KakaoMemberInfo getKakaoMemberInfo(@RequestHeader("Authorization") String accessToken);
}
