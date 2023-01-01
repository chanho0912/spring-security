package com.chan.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import com.chan.springsecurity.external.KakaoPublicApiClient;
import com.chan.springsecurity.external.KakaoOAuthClient;

@Configuration
public class HttpClientConfig {

    @Bean
    public KakaoOAuthClient kakaoAuthClient() {
        final WebClient webClient = WebClient.builder().baseUrl("https://kauth.kakao.com").build();
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                                      .build()
                                      .createClient(KakaoOAuthClient.class);
    }

    @Bean
    public KakaoPublicApiClient kakaoApiClient() {
        final WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com").build();
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                                      .build()
                                      .createClient(KakaoPublicApiClient.class);
    }

}
