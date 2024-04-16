package com.example.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.entity.GuestBook;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void testInsert() {
        // 300개 테스트 데이터 삽입
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("Guest Title..." + i)
                    .content("Guest Content..." + i)
                    .writer("user" + (i % 10))
                    .build();

            guestBookRepository.save(guestBook);
        });
    }

    @Test
    public void testList() {
        // 전체 리스트
        guestBookRepository.findAll().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void testRow() {
        // 특정 Row 조회
        System.out.println(guestBookRepository.findById(20L));
    }

    @Test
    public void testUpdate() {
        // 특정 Row 수정(title,content)
        GuestBook guestBook = guestBookRepository.findById(20L).get();
        guestBook.setTitle("수정 Title...");
        guestBook.setContent("수정 Content..");
        System.out.println(guestBookRepository.save(guestBook));
    }

    @Test
    public void testDelete() {
        // 특정 Row 삭제
        GuestBook guestBook = guestBookRepository.findById(190L).get();
        guestBookRepository.delete(guestBook);
    }

}
