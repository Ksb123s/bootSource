package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import com.example.todo.service.TodoServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {

    // 멤버 변수 초기화 -1) 생성자 2) setter
    private final TodoService service; // 의존성 주입

    // /로 접속시 list.html 보여주기
    @GetMapping(value = { "/", "/list" })
    public String getlist(Model model) {
        log.info("list 요청");

        List<TodoDto> list = service.getList();

        model.addAttribute("list", list);
        return "/todo/list";
    }

    @GetMapping("/create")
    public void getCreate() {
        log.info("create 요청");
        // return new String();
    }

    @PostMapping("/create")
    public String postCreate(TodoDto dto, RedirectAttributes rttr) {

        TodoDto result = service.create(dto);
        rttr.addFlashAttribute("msg", result.getId());
        return "redirect:/todo/list";
    }

    @GetMapping("/read")
    public void getRead(@RequestParam Long id, Model model) {
        log.info("read 요청 {}", id);
        TodoDto dto = service.getTodo(id);
        model.addAttribute("dto", dto);

    }

    // /todo/done
    @GetMapping("/done")
    public void getDone(Model model) {
        log.info("완료 목록 요청");

        List<TodoDto> list = service.getCompletedList();

        model.addAttribute("list", list);
    }

    @PostMapping("/update")
    public String postUpdate(Long id, RedirectAttributes rttr) {
        // id 값 받기
        log.info("업데이트 목록 요청");
        Long id2 = service.todoUpdate(id);
        rttr.addAttribute("id", id2);
        return "redirect:/todo/read";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id) {
        // id 값 받기
        log.info("삭제 목록 요청");
        service.todoDelete(id);

        return "redirect:/todo/list";
    }

}
