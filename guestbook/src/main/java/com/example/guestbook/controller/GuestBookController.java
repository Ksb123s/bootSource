package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("list 요청");
        PageResultDto<GuestBookDto, GuestBook> result = service.getList(requestDto);

        model.addAttribute("result", result);

    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(@ModelAttribute("requestDto") PageRequestDto requestDto, Long gno, Model model) {
        log.info("read or modify 요청");
        GuestBookDto dto = service.rowRead(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(@ModelAttribute("requestDto") PageRequestDto requestDto, GuestBookDto dto,
            RedirectAttributes rttr) {
        log.info("modify post 요청");
        Long gno = service.modify(dto);

        rttr.addAttribute("gno", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/guestbook/read";
    }

    @PostMapping("/delete")
    public String getDelete(@ModelAttribute("requestDto") PageRequestDto requestDto, Long gno,
            RedirectAttributes rttr) {
        log.info("delete 요청");
        service.Delete(gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/guestbook/list";
    }

    @GetMapping("/create")
    public void getCreate(GuestBookDto guestBookDto, @ModelAttribute("requestDto") PageRequestDto requestDto) {

    }

    @PostMapping("/create")
    public String postCreate(@Valid GuestBookDto dto, BindingResult result, RedirectAttributes rttr,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {

        if (result.hasErrors()) {
            return "/guestbook/create";
        }
        Long gno = service.create(dto);
        rttr.addAttribute("msg", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/guestbook/list";
    }

}
