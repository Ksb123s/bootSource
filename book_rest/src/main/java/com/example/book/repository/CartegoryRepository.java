package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.CartegoryEntity;
import java.util.List;
import java.util.Optional;

public interface CartegoryRepository extends JpaRepository<CartegoryEntity, Long> {

    Optional<CartegoryEntity> findByName(String name);
}
