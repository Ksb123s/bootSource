package com.example.jpa.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Address;
import com.example.jpa.entity.Member2;
import com.example.jpa.entity.Order;
import com.example.jpa.entity.Product;
import com.example.jpa.entity.Team2;

@SpringBootTest
public class JpqlRepositoryTest {

    @Autowired
    private Team2Repository team2Repository;

    @Autowired
    private Member2Repository member2Repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Team2 team2 = Team2.builder().name("team" + i).build();
            team2Repository.save(team2);

            Member2 member2 = Member2.builder()
                    .userName("User" + i)
                    .age(i)
                    .team2(team2)
                    .build();
            member2Repository.save(member2);

            Product product = Product.builder()
                    .name("제품" + i)
                    .price(i * 1000)
                    .stockAmount(i * 5)
                    .build();
            productRepository.save(product);

        });
    }

    @Test
    public void orderInsertTest() {

        Address address = new Address();
        address.setCity("서울시 종로구");
        address.setStreet("151-11");
        address.setZipcode("11017");

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member2 member2 = Member2.builder().id(1L).build();
            Product product = Product.builder().id(2L).build();

            Order order = Order.builder()
                    .member2(member2)
                    .product(product)
                    .homeAddress(address)
                    .build();
            orderRepository.save(order);
        });
    }

    @Test
    public void findMembersTest() {
        member2Repository.findByMembers().forEach(member -> {
            System.out.println(member);
        });
    }
}
