package com.chan.springsecurity.external;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import com.chan.springsecurity.dto.KakaoMemberInfo;

public interface KakaoPublicApiClient {

    @GetExchange("v2/user/me")
    KakaoMemberInfo getKakaoMemberInfo(@RequestHeader("Authorization") String accessToken);
}
