package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest.dto.SampleDto;

@Controller
public class BasicController {

    @GetMapping("/basic")
    @ResponseBody // 템플릿 찾지마세요 즉 [템플릿이 아닌 리턴값이 데이터]
    public String getMethodName() {
        return "반갑습니다.";
    }

    // ResponseEntity : 일반 컨트롤러에서도 리턴값이 데이터임으 의미
    @GetMapping(value = "/check2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> getCheck2(double height, double weight) {
        SampleDto dto = new SampleDto();
        dto.setMno(4L);
        dto.setFirstName(String.valueOf(height));
        dto.setLastName(String.valueOf(weight));

        if (height < 150) {
            return new ResponseEntity<SampleDto>(dto, HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<SampleDto>(dto, HttpStatus.OK);
        }
        // 200 -> OK

    }
}
