package com.example.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.Member;
import com.example.jpa.entity.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .userName("홍길동" + i)
                    .age(8 * i)
                    .roleType(RoleType.USER)
                    .description("user" + i)
                    .build();

            memberRepository.save(member);
        });

    }

    @Test
    public void readTest() {
        System.out.println(memberRepository.findById("user1"));

        System.out.println("----------------");
        // 특정 이름들 조회
        memberRepository.findByUserName("홍길동1").forEach(member -> System.out.println(member));

        System.out.println("----------------");
        memberRepository.findAll().forEach(i -> System.out.println(i));
    }

    @Test
    public void updateTest() {
        Optional<Member> result = memberRepository.findById("user1");

        result.ifPresent(member -> {
            member.setUserName("김길동");
            member.setAge(20);

            System.out.println(memberRepository.save(member));
        });
    }

    @Test
    public void deleteTest() {
        Optional<Member> result = memberRepository.findById("user8");

        memberRepository.delete(result.get());

    }
}
