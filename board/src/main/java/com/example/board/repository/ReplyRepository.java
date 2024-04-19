package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 삭제 with bno
    // @Query("DELETE FROM Reply r WHERE r.board.bno =:?1")

    @Modifying // delete, update 구문은 반드시 필요
    @Query("DELETE FROM Reply r WHERE r.board.bno =:bno")
    void deleteByBno(Long bno);

    List<Reply> getRepliesByBoardOrderByRno(Board board);

}
