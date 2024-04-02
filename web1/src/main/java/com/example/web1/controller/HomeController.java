package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
public class HomeController {
    // Error resolving template [], template might not exist or might not be
    // accessible by any of the configured Template Resolvers
    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        // c.e.web1.controller.HomeController : home 요청
        log.info("home 요청"); // SOUT

        // templates 아래 경로부터 시작 확장자 뺴고 파일명
        return "index";
    }

    // RedirectAttributes : redirect 시 데이터 전달
    // rttr.addAttributes("이름",값); : 파라메터로 전달
    // rttr.addFlashAttribute("이름", 값); session을 이용(임시 )해서 값을 저장

    @GetMapping("/ex3")
    public String ex3(RedirectAttributes rttr) {
        log.info("/ex3 요청");
        // request.sendRedirect("/qList.do");
        // path+="?bno="+ bno
        // return"redirect:/";

        // rttr.addAttribute("bno", 15);

        // session을 이용해서 값을 저장
        rttr.addFlashAttribute("bno", 15);
        return "redirect:/sample/basic"; // 경로 지정(다른 컨트롤러에 있는 경로 포함)
    }

    // IllegalStateException: Ambiguous mapping. Cannot map 'homeController' method
    // 같은 방식의 주소가 중복이면 오류 발생
    // @GetMapping("/ex3")
    // public void ex4() {

    // }

}
