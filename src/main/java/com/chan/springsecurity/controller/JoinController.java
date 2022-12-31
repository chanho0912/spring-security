package com.chan.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.chan.springsecurity.dto.MemberCreate;
import com.chan.springsecurity.service.MemberJoinService;

@Controller
public class JoinController {

    private final MemberJoinService memberJoinService;

    public JoinController(MemberJoinService memberJoinService) {this.memberJoinService = memberJoinService;}

    @PostMapping("/join")
    public String join(MemberCreate memberCreate) {
        memberJoinService.join(memberCreate);
        return "redirect:/login-form";
    }

}
