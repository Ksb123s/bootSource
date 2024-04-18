package com.example.board.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;

import groovyjarjarantlr4.v4.parse.BlockSetTransformer.setAlt_return;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/board")
public class BoardControllor {

    private final BoardService service;

    @GetMapping("/list")
    public void getlist(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("list 요청");

        model.addAttribute("result", service.getList(requestDto));

    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(@ModelAttribute("requestDto") PageRequestDto requestDto, @RequestParam Long bno, Model model) {
        log.info("read 요청" + bno);
        BoardDto dto = service.getRow(bno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String postModify(@ModelAttribute("requestDto") PageRequestDto requestDto, BoardDto dto,
            RedirectAttributes rttr) {
        log.info("modify post 요청");
        Long bno = service.modify(dto);

        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String postRemove(@ModelAttribute("requestDto") PageRequestDto requestDto, Long bno,
            RedirectAttributes rttr) {
        log.info("remove post 요청");
        service.deleteWithReply(bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/list";
    }

    @GetMapping("/create")
    public void getCreate(BoardDto boardDto, @ModelAttribute("requestDto") PageRequestDto requestDto) {

    }

    @PostMapping("/create")
    public String postCreate(@Valid BoardDto dto, BindingResult result, RedirectAttributes rttr,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        if (result.hasErrors()) {
            return "/board/create";
        }
        Long bno = service.createBoard(dto);
        if (bno == null) {
            log.info("사용자가 존재 하지 않습니다.");
            return "/board/create";
        }

        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/list";
    }

}
