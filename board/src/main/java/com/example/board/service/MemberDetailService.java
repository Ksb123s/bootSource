package com.example.board.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.board.constant.MemberRole;
import com.example.board.dto.MemberAuthDto;
import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemberDetailService implements UserDetailsService, MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findById(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("이메일을 확인해 주세요");
        }

        Member member = result.get();

        MemberDto memberDto = MemberDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .memberRole(member.getMemberRole())
                .build();
        // entity => dto
        // 시큐리티 로그인 =>> 회원정보 + 허가와 관련된 정보(사이트를 접근 여부)

        return new MemberAuthDto(memberDto);
    }

    @Override
    public String register(MemberDto insertDto) {
        // 중복 이메일 검사
        try {
            validateDuplicationMember(insertDto.getEmail());
        } catch (Exception e) {
            return e.getMessage();
        }

        Member member = Member.builder()
                .email(insertDto.getEmail())
                .name(insertDto.getName())
                .password(passwordEncoder.encode(insertDto.getPassword()))
                .memberRole(MemberRole.MEMBER)
                .build();
        memberRepository.save(member);
        return "success";
    }

    private void validateDuplicationMember(String email) throws Exception {
        Optional<Member> result = memberRepository.findById(email);
        if (result.isPresent())
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }

}
