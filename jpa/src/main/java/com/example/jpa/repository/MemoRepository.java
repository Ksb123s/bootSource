package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Memo;

// <Entity,Entity 에서 기본키 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // DAO 역할
    // Mno 가 5보다 작은
    List<Memo> findByMnoLessThan(Long mno);

    // Mno 가 10보다 작은(내림차순)
    List<Memo> findByMnoLessThanOrderByMnoDesc(Long mno);

    // Mno 가 10>=50 and <= 70
    List<Memo> findByMnoBetween(Long mno, Long Mno);

}
