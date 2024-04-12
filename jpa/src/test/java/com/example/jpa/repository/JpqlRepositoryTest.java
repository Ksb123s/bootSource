package com.example.jpa.repository;

import static org.mockito.ArgumentMatchers.matches;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

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
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Team2 team2 = Team2.builder().name("team" + i).build();
            team2Repository.save(team2);

            Member2 member2 = Member2.builder()
                    .userName("User" + i)
                    .team2(team2)
                    .age(i)
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

            Order order = Order.builder().member2(member2).product(product).homAddress(address).build();
            orderRepository.save(order);
        });
    }

    @Test
    public void JPQLTest() {
        // List<Member2> list = member2Repository.findbyMembers();

        // list.forEach(System.out::println);

        // System.out.println(member2Repository.findbyMembers(Sort.by("age")));
        // List<Object[]> list = member2Repository.findbyMembers2();
        // for (Object[] objects : list) {
        // System.out.println(Arrays.toString(objects));
        // System.out.printf("username = %s, age = %d\n ", objects[0], objects[1]);
        // }

        // test findByHome
        // List<Address> addr = orderRepository.findByAddress();
        // addr.forEach(System.out::println);

        // List<Object[]> list = orderRepository.findByOrders();
        // for (Object[] objects : list) {
        // Member2 member2 = (Member2) objects[0];
        // Product product = (Product) objects[1];
        // Long id = (Long) objects[2];
        // // System.out.printf("members = %s, product = %s, id = %d\n ", objects[0],
        // // objects[1], objects[2]);
        // System.out.print(member2 + ",");
        // System.out.print(product + ",");
        // System.out.println(id);
        // }

        // 특정 나이 이상 멤버 리스트
        // List<Member2> list2 = member2Repository.findByAgeList(6);
        // list2.forEach(System.out::println);

        // List<Member2> list3 =
        // member2Repository.findByTeamEqual(Team2.builder().id(2L).build());
        // list3.forEach(System.out::println);

        // List<Member2> list4 = member2Repository.findByTeamEqual(3L);
        // list4.forEach(System.out::println);

        // List<Object[]> list5 = member2Repository.aggregate();

        // for (Object[] objects : list5) {
        // System.out.println(Arrays.toString(objects));
        // System.out.println("회원수" + objects[0]);
        // System.out.println("나이 합" + objects[1]);
        // System.out.println("나이 평균" + objects[2]);
        // System.out.println("최대 나이" + objects[3]);
        // System.out.println("최소 나이" + objects[4]);

        // }

        List<Member2> list6 = member2Repository.findByTeamMember("team2");
        list6.forEach(System.out::println);

        List<Object[]> list7 = member2Repository.findByTeamMember2("team3");

        for (Object[] objects : list7) {
            System.out.println(Arrays.toString(objects));
            Member2 member2 = (Member2) objects[0];
            Team2 Team2 = (Team2) objects[1];
            System.out.println(member2);
            System.out.println(Team2);

        }

    }
}
