package com.example.board.reopository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import com.example.board.repository.ReplyRepository;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 1).forEach(i -> {
            long bno = (long) (Math.random() * 100) + i;

            Board board = Board.builder().bno(bno + 1L).build();

            Reply reply = Reply.builder()
                    .text("Reply..." + i)
                    .replyer("geust" + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }
}
