package com.chan.springsecurity.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoMemberInfo(
        Long id,
        // UTC 존으로 반환받기 때문에 굳이 ZonedDateTime으로 받지 않음
        // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
        @JsonProperty("connected_at")
        LocalDateTime connectedAt,
        Properties properties,
        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {}

record Properties(String nickname) {}

record Profile(String nickname) {}

record KakaoAccount(
        @JsonProperty("profile_nickname_needs_agreement")
        Boolean profileNicknameNeedsAgreement,
        Profile profile,
        @JsonProperty("has_email")
        Boolean hasEmail,
        @JsonProperty("email_needs_agreement")
        Boolean emailNeedsAgreement,
        @JsonProperty("is_email_valid")
        Boolean isEmailValid,
        @JsonProperty("is_email_verified")
        Boolean isEmailVerified,
        String email
) {}



/*
{
  "id": ...,
  "connected_at": "...",
  "properties": {
    "nickname": "..."
  },
  "kakao_account": {
    "profile_nickname_needs_agreement": false,
    "profile": {
      "nickname": "..."
    },
    "has_email": true,
    "email_needs_agreement": false,
    "is_email_valid": true,
    "is_email_verified": true,
    "email": "..."
  }
}
 */
