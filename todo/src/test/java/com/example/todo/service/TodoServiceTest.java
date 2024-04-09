package com.example.todo.service;

import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.dto.TodoDto;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService service;

    // Service <==> Repository 동작 확인
    // service 가 잘 동작하는지 확인

    @Test
    public void serviceList() {
        System.out.println(service.getList());
    }

    @Test
    public void serviceCreate() {
        TodoDto dto = new TodoDto();
        dto.setTitle("스프링 공부");
        dto.setImportant(true);
        System.out.println(service.create(dto));
    }

    @Test
    public void serviceGetRow() {
        System.out.println(service.getTodo(13L));

    }

    @Test
    public void serviceGetComp() {
        System.out.println(service.getCompletedList());

    }
}
