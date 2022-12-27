package com.chan.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chan.springsecurity.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
