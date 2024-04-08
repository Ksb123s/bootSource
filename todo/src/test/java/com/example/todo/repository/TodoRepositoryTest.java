package com.example.todo.repository;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {
    // Dao == TodoRepository
    // service 에서 호출하는 메소드 테스트

    @Autowired
    private TodoRepository todoRepository;

    // todo 삽입 메소드
    @Test
    public void todoInsertTest() {

        // LongStream.range(1, 11).forEach(i -> {
        // Todo todo = Todo.builder().title("Title" +
        // i).completed(true).important(false).build();
        // todoRepository.save(todo);
        // });
        LongStream.range(1, 11).forEach(i -> {
            Todo todo = Todo.builder().title("강아지 목욕" + i).build();
            todoRepository.save(todo);
        });
    }

    // todo 전체 목록 조회
    @Test
    public void todoListAll() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    // todo 개별 목록 조회
    @Test
    public void todogetRow() {
        System.out.println(todoRepository.findById(1L));
    }

    // todo 완료 목록 조회
    @Test
    public void todoCompletedList() {
        todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
    }

    // todo 중요 목록 조회
    @Test
    public void todoImportantList() {
        todoRepository.findByImportant(true).forEach(todo -> System.out.println(todo));
    }

    // todo 수정
    @Test
    public void todoUpdateTest() {
        Todo todo = todoRepository.findById(5L).get();
        todo.setTitle("강아지 산책");
        todo.setImportant(true);
        todo.setCompleted(true);
        System.out.println(todoRepository.save(todo));
    }

    // todo 삭제
    @Test
    public void todoDeleteTest() {
        Todo todo = todoRepository.findById(8L).get();

        todoRepository.delete(todo);
    }
}
