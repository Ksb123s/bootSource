package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import org.springframework.data.domain.Pageable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.jpa.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        // LongStream.range(1, 100) : 1~99
        LongStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("user" + (i % 10))
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void readTest() {
        System.out.println(boardRepository.findById(5L));
    }

    @Test
    public void getListTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Optional<Board> result = boardRepository.findById(26L);

        // result.get()
        result.ifPresent(board -> {
            board.setTitle("Update Title....");
            board.setContent("Update Content");
            System.out.println(boardRepository.save(board));
        });
    }

    @Test
    public void deleteTest() {
        // entity 찾기
        Optional<Board> result = boardRepository.findById(15L);

        // 삭제
        boardRepository.delete(result.get());
    }

    @Test
    public void queryMethodeTest() {
        List<Board> list1 = boardRepository.findList();
        list1.forEach(System.out::println);
        // List<Board> list1 = boardRepository.findByTitle("Title");
        // System.out.println("findByTitle :" + list1.size());
        // List<Board> list2 = boardRepository.findByWriter("user");
        // System.out.println("findByWriter :" + list2.size());
        // List<Board> list2 = boardRepository.findByTitleStartingWith("Title");
        // System.out.println("findByTitleStartingWith :" + list2.size());
        // List<Board> list3 = boardRepository.findByTitleEndingWith("Title");
        // System.out.println("findByTitleEndingWith :" + list3.size());
        // List<Board> list4 = boardRepository.findByTitleContaining("Title");
        // System.out.println("findByTitleContaining :" + list4.size());
        // List<Board> list5 = boardRepository.findByTitleLike("Title");
        // System.out.println("findByTitleLike :" + list5.size());

        // List<Board> list6 = boardRepository.findByWriterStartingWith("user");
        // System.out.println("findByWriterStartingWith :" + list6.size());

        // List<Board> list7 = boardRepository.findByTitleContainingOrContent("Title",
        // "Content");
        // System.out.println("findByTitleContainingOrContent :" + list7.size());
        // List<Board> list8 =
        // boardRepository.findByTitleContainingOrContentContaining("Title", "Content");
        // System.out.println("findByTitleContainingOrContentContaining :" +
        // list8.size());

        // List<Board> list9 =
        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 50L);
        // System.out.println("findByTitleContainingAndIdGreaterThan :" + list9.size());

        // List<Board> list10 = boardRepository.findByIdGreaterThanOrderByIdDesc(50L);
        // System.out.println("findByIdGreaterThanOrderByIdDesc :" + list10.size());

        // Pageable pageable = PageRequest.of(0, 10);

        // List<Board> list1 = boardRepository.findByIdGreaterThanOrderByIdDesc(50L,
        // pageable);

        // list1.forEach(System.out::println);

    }
}
