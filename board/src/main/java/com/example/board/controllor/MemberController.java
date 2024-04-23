package com.example.board.controllor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.dto.MemberDto;
import com.example.board.dto.PageRequestDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getLogin(@ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("login 폼 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void getRegister(@ModelAttribute("requestDto") PageRequestDto requestDto, MemberDto dto) {
        log.info("register 폼 요청");
    }

    @PostMapping("/register")
    public String postRegister(@Valid MemberDto dto, BindingResult result,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        if (result.hasErrors()) {
            return "member/register";
        }

        return "redirect:/member/login";
    }

}
