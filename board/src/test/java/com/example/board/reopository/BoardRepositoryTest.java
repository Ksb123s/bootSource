package com.example.board.reopository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@gamil.com").build();

            Board board = Board.builder()
                    .title("title" + i)
                    .content("content..." + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void readBoard() {

        Board board = boardRepository.findById(2L).get();

        System.out.println(board);
        // fetchType.eager
        // Board(bno=2, title=title1, content=content...1,
        // writer=Member(email=user1@gamil.com, password=1111, name=USER1))

        // fetchType.lazy
        // Board(bno=2, title=title1, content=content...1)

        System.out.println(board.getWriter());
    }

    @Test
    public void testList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        // Page<Object[]> list = boardRepository.list(pageable);
        // for (Object[] objects : list) {
        // System.out.println(Arrays.toString(objects));
        // Board board = (Board) objects[0];
        // Member member = (Member) objects[1];
        // Long reply = (Long) objects[2];

        // System.out.println(board + " " + member + " " + reply);

        // }
    }

    @Test
    public void testGetRow() {
        Object[] list = boardRepository.getrow(3L);

        System.out.println(Arrays.toString(list));
    }

    @Transactional
    @Test
    public void testRemove() {
        // 자식 삭제
        replyRepository.deleteByBno(10L);
        // 부모 삭제
        boardRepository.deleteById(10L);
    }
}
