package com.example.movie.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.movie.dto.MemberDto;

public class MovieUserServiceImpl implements UserDetailsService, MovieUserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 시큐리티 로그인 메소드
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    @Override
    public String register(MemberDto insertDto) throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public void nickNameUpdate(MemberDto upMemberDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nickNameUpdate'");
    }

    @Override
    public void pwdUpdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pwdUpdate'");
    }

}
