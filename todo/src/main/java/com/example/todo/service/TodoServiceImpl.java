package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor // @AutoWired == private TodoRepository todoRepository
@Service
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> getList() {
        // 미완료 목록
        List<Todo> list = todoRepository.findByCompleted(false);
        // Todo => TodoDto 변환
        // List<TodoDto> todoList = new ArrayList<>();

        // list.forEach(todo -> todoList.add(entityToDto(todo)));

        List<TodoDto> todoList = list.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());
        return todoList;
    }

    @Override
    public TodoDto create(TodoDto dto) {
        // TodoDto ==> Todo 변환

        Todo entity = todoRepository.save(dtoToEntity(dto));
        return entityToDto(entity);
    }

    @Override
    public void todoDelete(Long id) {

        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto entityToDto(Todo entity) {
        return TodoDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .completed(entity.getCompleted())
                .important(entity.getImportant())
                .build();
    }

    @Override
    public Todo dtoToEntity(TodoDto dto) {
        return Todo.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .completed(dto.getCompleted())
                .important(dto.getImportant())
                .build();
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).get();

        return entityToDto(todo);
    }

    @Override
    public List<TodoDto> getCompletedList() {
        List<Todo> compList = todoRepository.findByCompleted(true);

        // List<TodoDto> todoList = new ArrayList<>();

        // compList.forEach(todo -> todoList.add(entityToDto(todo)));
        List<TodoDto> todoList = compList.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());

        return todoList;
    }

    @Override
    public Long todoUpdate(Long id) {
        Todo entity = todoRepository.findById(id).get();
        entity.setCompleted(true);

        Todo todo = todoRepository.save(entity);

        return todo.getId();
    }

    @Override
    public TodoDto entityToDto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entityToDto'");
    }

    @Override
    public Todo dtoToEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dtoToEntity'");
    }
}
