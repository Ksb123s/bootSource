package com.example.todo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class TodoController {

    // /로 접속시 list.html 보여주기
    @GetMapping(value = { "/", "/todo/list" })
    public String getlist() {
        log.info("list 요청");
        return "/todo/list";
    }

}
