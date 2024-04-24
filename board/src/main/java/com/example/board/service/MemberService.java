package com.example.board.service;

import com.example.board.dto.MemberDto;

public interface MemberService {

    public String register(MemberDto insertDto);

    // + 회원수정, 회원탈퇴 => default dtoToEntity ,entityToDto 등 제작
}
