package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class BookController {

    @GetMapping("/")
    public String getHome() {
        log.info("Home 요청");
        return "home";
    }

    @GetMapping("/list")
    public String getList() {
        log.info("list 요청 ");

        return "/book/list";
    }

    @GetMapping("/create")
    public String getCreate() {
        log.info("create 요청 ");

        return "/book/create";
    }

    @GetMapping("/read")
    public String getRead() {
        log.info("read 요청 ");

        return "/book/read";
    }

    @GetMapping("/modify")
    public String getModify() {
        log.info("modify 요청 ");

        return "/book/modify";
    }

}
