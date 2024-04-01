package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.AddDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("calc")
public class RulesController {

    @GetMapping("rules")
    public void getRules() {
        log.info("/calc/rules 페이지 요청");
    }

    @PostMapping("rules")
    public String postRules(@ModelAttribute("cDto") AddDto addDto) {
        log.info("/calc/rules 포스트 요청");
        switch (addDto.getOp()) {
            case "+":
                addDto.setResult(addDto.getNum1() + addDto.getNum2());
                break;
            case "-":
                addDto.setResult(addDto.getNum1() - addDto.getNum2());
                break;
            case "*":
                addDto.setResult(addDto.getNum1() * addDto.getNum2());
                break;
            case "/":
                addDto.setResult(addDto.getNum1() / addDto.getNum2());
                break;
        }

        return "/calc/result";
    }

}
