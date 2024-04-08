package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Child;
import com.example.jpa.entity.Parent;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void insertTest() {
        Parent parent = Parent.builder().name("Parent1").build();
        parentRepository.save(parent);
        LongStream.range(1, 3).forEach(i -> {
            Child child = Child.builder().name("Child" + i).parent(parent).build();
            childRepository.save(child);
        });

    }

    @Test
    public void insertCascadeTest() {
        Parent parent = Parent.builder().name("Parent3").build();

        LongStream.range(1, 3).forEach(i -> {
            Child child = Child.builder().name("Child" + i).parent(parent).build();
            parent.getChildList().add(child);
        });

        parentRepository.save(parent);
    }

    @Test
    public void deleteTest() {
        // 부모가 자식을 가지고 있는경우 자식의 부모 아이디를 변경후 부모 삭제
        Parent p1 = Parent.builder().id(1L).build();

        // 부모를 null 지정
        // Child c1 = Child.builder().id(1L).parent(null).build();
        // Child c2 = Child.builder().id(2L).parent(null).build();

        // 자식 먼저 삭제
        Child c1 = Child.builder().id(1L).build();
        Child c2 = Child.builder().id(2L).build();
        childRepository.delete(c1);
        childRepository.delete(c2);

        parentRepository.delete(p1);

    }

    @Test
    public void deleteCascadeTest() {
        Parent parent = Parent.builder().id(52L).build();

        Child c1 = Child.builder().id(102L).build();
        parent.getChildList().add(c1);
        Child c2 = Child.builder().id(103L).build();
        parent.getChildList().add(c2);

        parentRepository.delete(parent);
    }

    // @Transactional
    @Test
    public void deleteOrphanTest() {
        // 기존 자식 여부 체크
        Parent p = parentRepository.findById(102L).get();
        // org.hibernate.LazyInitializationException: failed to lazily initialize a
        // collection of role: com.example.jpa.entity.Parent.childList: could not
        // initialize proxy
        // fetch 타입이 lazy 이기 때문에 getchildlist를 가져오지 않음 @Transactional 사용
        System.out.println("기존 자식 :" + p.getChildList());
        p.getChildList().remove(0);

        parentRepository.save(p);

    }
}
