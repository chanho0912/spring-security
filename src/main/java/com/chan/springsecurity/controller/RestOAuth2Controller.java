package com.chan.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class RestOAuth2Controller {

    @GetMapping("naver")
    public String naverOAuth2Login() {
        return "naver";
    }
}
