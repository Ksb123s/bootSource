package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

}
