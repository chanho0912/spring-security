package com.chan.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    public KakaoClient kakaoClient() {
        final WebClient webClient = WebClient.builder().baseUrl("https://kauth.kakao.com").build();
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                                      .build()
                                      .createClient(KakaoClient.class);
    }

}
