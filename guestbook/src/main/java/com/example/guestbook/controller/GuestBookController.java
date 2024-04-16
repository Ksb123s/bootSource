package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestbook.service.GuestBookService;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(Model model) {
        log.info("list 요청");

        model.addAttribute("list", service.getList());
    }

}
