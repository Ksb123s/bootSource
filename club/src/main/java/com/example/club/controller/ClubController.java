package com.example.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.club.dto.ClubMemberDto;
import com.example.club.service.ClubOauth2UserDetailService;
import com.example.club.service.ClubUserDetailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
@Log4j2
@RequestMapping("/club")
public class ClubController {

    private final ClubUserDetailService service;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager")
    public void getmanager() {
        log.info("manager 요청");
    }

    @PreAuthorize("hasAnyRole('USER','MANAGER', 'ADMIN')")
    @GetMapping("/member")
    public void getMember() {
        log.info("member 요청");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void getAdmin() {
        log.info("admin 요청");
    }

    @GetMapping("/member/login")
    public void getLogin() {
        log.info("Login 페이지 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/member/register")
    public void getRegister() {
        log.info("회원 가입 요청");
    }

    @PostMapping("/member/register")
    public String postRegister(ClubMemberDto member) {
        log.info("회원가입 요청 {}", member);
        // 회원가입 서비스 호출
        String email = service.register(member);
        log.info("회원가입 완료 email : {}", email);
        return "redirect:/club/member/login";
    }

}
