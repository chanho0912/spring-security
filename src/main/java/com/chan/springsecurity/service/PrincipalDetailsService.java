package com.chan.springsecurity.service;

import java.util.Objects;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chan.springsecurity.model.Member;
import com.chan.springsecurity.model.PrincipalDetails;
import com.chan.springsecurity.repository.MemberRepository;

/**
 * 시큐리티 설정에서 loginProcessingUrl("/login");
 * /login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어있는 loadUserByUsername 함수 실행
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public PrincipalDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Member memberEntity = memberRepository.findByUsername(username);

        if (Objects.nonNull(memberEntity)) {
            return new PrincipalDetails(memberEntity);
        }

        return null;
    }
}
