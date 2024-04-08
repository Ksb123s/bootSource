package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.entity.Todo;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // completed column 값에 따라 조회
    List<Todo> findByCompleted(Boolean completed);

    // completed column 값에 따라 조회 순서 정렬
    // where completed=? order by id Desc
    List<Todo> findByCompletedOrderByIdDesc(Boolean completed);

    // important column 값에 따라 조회
    List<Todo> findByImportant(Boolean important);
}
