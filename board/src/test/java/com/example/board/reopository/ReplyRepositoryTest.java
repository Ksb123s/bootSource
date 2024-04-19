package com.example.board.reopository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply..." + i)
                    .replyer("geust" + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }

    @Transactional
    @Test
    public void getRow() {

        Reply reply = replyRepository.findById(50l).get();

        System.out.println(reply.getBoard());
    }

    @Transactional
    @Test
    public void getReplies() {
        Board board = Board.builder().bno(91L).build();
        List<Reply> replies = replyRepository.getRepliesByBoardOrderByRno(board);

        replies.forEach(System.out::println);
    }
}
