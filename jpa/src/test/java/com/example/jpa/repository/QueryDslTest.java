package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testQueryDsl() {

    }
}
