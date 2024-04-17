package com.example.board.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.board.dto.PageRequestDto;

public interface SearchBoardRepository {
    // @Query(SELECT m , t from Member m join m.team t = ?1)
    // 전체조회 시 Board ,Member, Reply 정보를 다 조회
    Page<Object[]> list(String type, String keyword, Pageable pageable);

    // 상세 조회
    Object[] getrow(Long bno);
}
