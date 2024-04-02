package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.LoginDto;
import com.example.web1.dto.MemberDto;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("member")
public class MemberControllor {

    @GetMapping("/login")
    public void login(LoginDto loginDto) {

        log.info("로그인 페이지 요청");
    }

    // @PostMapping("/login")
    // public void postLogin(String email, String name) {

    // log.info("로그인 정보 요청");

    // log.info("email {}", email);
    // log.info("name {}", name);
    // }

    // @Valid LoginDto : LoginDto의 유효성 검사
    @PostMapping("/login")
    public String postLogin(@Valid LoginDto dto, BindingResult result) {

        log.info("로그인 정보 요청");

        log.info("email {}", dto.getEmail());
        log.info("name {}", dto.getName());

        // 유효성 검증을 통과하지 못하면 복귀
        if (result.hasErrors()) {
            return "/member/login";
        }

        return "/member/info";
    }

    // 데이터 보내기
    // request.setAttribute("이름", "값") == Model

    // member/join + get
    @GetMapping("/join")
    public void join(MemberDto memberDto) {

        log.info("/join 페이지 요청");
    }

    // member/join + post
    @PostMapping("/join")
    public String joinPost(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "/member/join";
        }

        return "redirect:/member/login";
    }

}
