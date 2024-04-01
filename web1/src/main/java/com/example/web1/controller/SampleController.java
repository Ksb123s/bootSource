package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/sample")
public class SampleController {
    // string, void 형태의 메소드 작성

    // 404 :경로 없음(컨트롤러에 메핑 주소 확인)
    // 500 : Error resolving template [sample/basic], template might not exist or
    // might not be accessible
    // templates 폴더 확인
    // 400: Bad Request, status=500

    // 리턴타입 결정
    // void : 경로와 일치하는 곳에 탬플릿이 존재할 때
    // String : 경로와 일치하는 곳에 템플릿이 없을 때(템플릿의 위치와 관계없이 지정 가능)

    // http://localhost:8080/sample/basic 요청

    @GetMapping("/basic")
    public void basic() {
        log.info("/sample/basic 요청");
    }

    @GetMapping("/ex1")
    public void ex1() {
        log.info("/sample/ex1 요청");
    }

    @GetMapping("/ex2")
    public String ex2() {
        log.info("/sample/ex2 요청");
        return "/index";

    }

}
