package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Member2;

public interface Member2Repository extends JpaRepository<Member2, Long> {

    @Query("SELECT m FROM Member2 m")
    List<Member2> findByMembers();
}
