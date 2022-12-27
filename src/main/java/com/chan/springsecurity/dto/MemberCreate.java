package com.chan.springsecurity.dto;

public record MemberCreate(
        String username, String password, String email) {
}
