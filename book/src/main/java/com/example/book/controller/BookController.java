package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.book.dto.BookDto;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @GetMapping("/list")
    public String getList(Model model) {
        log.info("list 요청 ");
        List<BookDto> list = service.getList();
        model.addAttribute("list", list);
        return "/book/list";
    }

    @GetMapping("/create")
    public void getCreate(BookDto bookDto, Model model) {
        log.info("create 요청 ");
        model.addAttribute("categories", service.categoryNameList());
        model.addAttribute("publishers", service.pulisherNameList());
    }

    @PostMapping("/create")
    public String postCreate(@Valid BookDto bookDto, BindingResult result, RedirectAttributes rttr, Model model) {
        log.info("post create 요청 ");
        if (result.hasErrors()) {
            model.addAttribute("categories", service.categoryNameList());
            model.addAttribute("publishers", service.pulisherNameList());

            return "/book/create";
        }
        Long id = service.bookCreate(bookDto);
        rttr.addFlashAttribute("result", id);
        return "redirect:/book/list";
    }

    @GetMapping(value = { "/modify", "/read" })
    public void getModify(Model model, Long id) {
        log.info("modify or read 요청 ");
        model.addAttribute("dto", service.getRow(id));
    }

    @PostMapping("/modify")
    public String postModify(BookDto dto, RedirectAttributes rttr) {
        Long id = service.bookUpate(dto);

        rttr.addAttribute("id", id);
        // 조회 화면으로 이동
        return "redirect:/book/read";
    }

    @PostMapping("/delete")
    public String getDelete(Long id) {
        log.info("도서 삭제 요청 ");

        service.bookDelete(id);
        return "redirect:/book/list";
    }

}
