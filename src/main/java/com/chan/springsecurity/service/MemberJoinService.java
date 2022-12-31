package com.chan.springsecurity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chan.springsecurity.dto.MemberCreate;
import com.chan.springsecurity.model.Member;
import com.chan.springsecurity.repository.MemberRepository;

@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberJoinService(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void join(MemberCreate memberCreate) {
        final Member member = Member.builder()
                                    .username(memberCreate.username())
                                    .password(bCryptPasswordEncoder.encode(memberCreate.password()))
                                    .email(memberCreate.email())
                                    .role("ROLE_USER")
                                    .build();

        memberRepository.save(member);
    }
}
