package com.chan.springsecurity.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.chan.springsecurity.dto.MemberCreate;
import com.chan.springsecurity.model.Member;
import com.chan.springsecurity.repository.MemberRepository;

@Controller
public class JoinController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinController(MemberRepository memberRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/join")
    public String join(MemberCreate memberCreate) {
        final Member member = Member.builder()
                                    .username(memberCreate.username())
                                    .password(bCryptPasswordEncoder.encode(memberCreate.password()))
                                    .email(memberCreate.email())
                                    .role("ROLE_USER")
                                    .build();

        memberRepository.save(member);
        return "redirect:/login";
    }

}
