package com.example.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.SampleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
    컨트롤러
    @Controller
        - 메소드가 끝나고 찾는 대상은 템플릿
    @RestController
        - 데이터 자체 리턴 가능 
        - 객체 -> json 변환하는 컨버터 필요
 
*/
@RestController
public class RestControllerTest {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping(value = "/sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public SampleDto getSample() {
        SampleDto dto = new SampleDto();
        dto.setMno(4L);
        dto.setFirstName("홍");
        dto.setLastName("길동");
        return dto;
    }

    @GetMapping(value = "/sample2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SampleDto> getSampleList() {
        List<SampleDto> list = new ArrayList<>();
        LongStream.rangeClosed(1, 10).forEach(i -> {
            SampleDto dto = new SampleDto();
            dto.setMno(i);
            dto.setFirstName("홍");
            dto.setLastName("길동");
            list.add(dto);
        });

        return list;
    }

    // 데이터 + 상태코드(Http 상태코드 - 200,404,500...)
    // ResponseEntity
    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> getCheck(double height, double weight) {
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

    @GetMapping("/product/{cat}/{pid}")
    public String[] getMethodName(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
        return new String[] {
                "category : " + cat,
                "productId : " + pid
        };
    }

}
