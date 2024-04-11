package com.example.book.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.BookEntity;
import com.example.book.entity.CartegoryEntity;
import com.example.book.entity.PublisherEntity;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private CartegoryRepository cartegoryRepository;

    @Test
    public void insertCartegory() {
        CartegoryEntity cartegory1 = CartegoryEntity.builder().name("컴퓨터").build();
        cartegoryRepository.save(cartegory1);
        CartegoryEntity cartegory2 = CartegoryEntity.builder().name("경제/경영").build();
        cartegoryRepository.save(cartegory2);
        CartegoryEntity cartegory3 = CartegoryEntity.builder().name("인문").build();
        cartegoryRepository.save(cartegory3);
        CartegoryEntity cartegory4 = CartegoryEntity.builder().name("소설").build();
        cartegoryRepository.save(cartegory4);
        CartegoryEntity cartegory5 = CartegoryEntity.builder().name("자기계발").build();
        cartegoryRepository.save(cartegory5);
    }

    @Test
    public void insertPublisher() {
        PublisherEntity publisher1 = PublisherEntity.builder().name("로드북").build();
        publisherRepository.save(publisher1);
        PublisherEntity publisher2 = PublisherEntity.builder().name("다산").build();
        publisherRepository.save(publisher2);
        PublisherEntity publisher3 = PublisherEntity.builder().name("웅진지식하우스").build();
        publisherRepository.save(publisher3);
        PublisherEntity publisher4 = PublisherEntity.builder().name("비룡소").build();
        publisherRepository.save(publisher4);
        PublisherEntity publisher5 = PublisherEntity.builder().name("을유문회사").build();
        publisherRepository.save(publisher5);

    }

    @Test
    public void insertBook() {
        LongStream.rangeClosed(1, 20).forEach(i -> {

            BookEntity entity = BookEntity.builder()
                    .title("javaScript" + i)
                    .writer("홍길동" + i)
                    .price(50000 * (int) i)
                    .salePrice(500 * (int) i)
                    .cartegory(CartegoryEntity.builder().id((i % 5) + 1).build())
                    .publisher(PublisherEntity.builder().id((i % 5) + 1).build())
                    .build();
            bookRepository.save(entity);
        });
    }

    @Transactional
    @Test
    public void testBookList() {
        List<BookEntity> books = bookRepository.findAll();
        books.forEach(book -> {
            System.out.println(book);
            System.out.println("출판사 :" + book.getPublisher().getName());
            System.out.println("분야 :" + book.getCartegory().getName());
        });
    }

    @Transactional
    @Test
    public void testCartegoryNameList() {
        List<CartegoryEntity> categorys = cartegoryRepository.findAll();
        categorys.forEach(category -> {
            System.out.println("분야 :" + category.getName());
        });

        // List<String> cateList = new ArrayList<>();

        // categorys.forEach(list -> cateList.add(list.getName()));

        List<String> cateList = categorys.stream().map(i -> i.getName()).collect(Collectors.toList());

        cateList.forEach(list -> System.out.println(list));
    }
}
