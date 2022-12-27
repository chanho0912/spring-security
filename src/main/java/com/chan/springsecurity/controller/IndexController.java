package com.chan.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping({ "", "/" })
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    @GetMapping("/join-proc")
    @ResponseBody
    public String joinProc() {
        return "회원 가입 완료!";
    }

    @GetMapping("only-for-admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public String onlyForAdmin() {
        return "hello admin~!";
    }

    @GetMapping("only-for-manager-and-admin")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @ResponseBody
    public String onlyForManagerAndAdmin() {
        return "hello manager or admin~!";
    }
}
