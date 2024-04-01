package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.LoginDto;

@Log4j2
@Controller
@RequestMapping("member")
public class MemberControllor {

    @GetMapping("/login")
    public void login() {

        log.info("로그인 페이지 요청");
    }

    // @PostMapping("/login")
    // public void postLogin(String email, String name) {

    // log.info("로그인 정보 요청");

    // log.info("email {}", email);
    // log.info("name {}", name);
    // }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("mDto") LoginDto dto, @ModelAttribute("page") int page, Model model) {

        log.info("로그인 정보 요청");

        log.info("email {}", dto.getEmail());
        log.info("name {}", dto.getName());
        log.info("page {}", page);
        // model.addAttribute("page", page);
        // 기본방식 forward
        return "/member/info";
    }

    // 데이터 보내기
    // request.setAttribute("이름", "값") == Model

}
