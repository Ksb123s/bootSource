package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityTest {

    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 암호화, 원 비밀번호와 암호화된 비밀번호의 매치 여부

    @Test
    public void testEncoder() {
        String password = "1111";

        // 원 비밀번호 암호화
        String encoderPassword = passwordEncoder.encode(password);

        System.out.println("password : " + password + " , encode password : " + encoderPassword);

        System.out.println(passwordEncoder.matches(password, encoderPassword));
    }
}
