package com.example.movie.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.constant.MemberRole;
import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordChangeDto;
import com.example.movie.entity.Member;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MovieUserServiceImpl implements UserDetailsService, MovieUserService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 요청 {}", username);

        // 시큐리티에서 사용하는 로그인 메소드
        // User 로 리턴 or User 구현한 CustomUser 로 리턴
        // Optional<Member> result = memberRepository.findById(null);

        // if(result.isPresent())
        // Member member = result.get();

        // return User.builder()
        // .username(member.getEmail())
        // .password(member.getPassword())
        // .roles(member.getRole().toString())
        // .build();

        Optional<Member> result = memberRepository.findByEmail(username);

        if (!result.isPresent())
            throw new UsernameNotFoundException("Check Email");

        Member member = result.get();
        // entity => dto

        return new AuthMemberDto(entityToDto(member));
    }

    @Override
    public String register(MemberDto insertDto) throws IllegalStateException {

        // 중복 이메일 확인
        validateDuplicateEmail(insertDto.getEmail());

        // 비밀번호 암호화
        insertDto.setPassword(passwordEncoder.encode(insertDto.getPassword()));
        // 권한 부여
        insertDto.setRole(MemberRole.MEMBER);

        Member member = memberRepository.save(dtoToEntity(insertDto));
        return member.getEmail();
    }

    // 중복 이메일 검사
    private void validateDuplicateEmail(String email) throws IllegalStateException {
        Optional<Member> result = memberRepository.findByEmail(email);

        if (result.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Transactional
    @Override
    public void nickNameUpdate(MemberDto upMemberDto) {
        // 1) select 2) select 결과에 따라 insert or update
        // memberRepository.save(dtoToEntity(upMemberDto));

        memberRepository.updateNickName(upMemberDto.getNickname(), upMemberDto.getEmail());
    }

    @Override
    public void passwordUpdate(PasswordChangeDto pDto) throws IllegalStateException {
        // 현재 이메일과 비밀번호 일치 여부 => true => 비밀번호 변경
        // select => 결과 있다면 => update
        Member member = memberRepository.findByEmail(pDto.getEmail()).get();
        // passwordEncoder.encode(1111) : 암호화 시 사용
        // passwordEncoder.matches(rawPassword, encodePassword) : 1111,
        // {bcrypt}$2a$10$Ncn8nEcbG4PT0gywxh/HU.9x4nhHSBPEE56.wvdCJNe0N/QzNskUe

        // 비밀번호는 암호화된 상태
        if (!passwordEncoder.matches(pDto.getCurrentPassword(), member.getPassword())) {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        } else {
            member.setPassword(passwordEncoder.encode(pDto.getNewPassword()));
            memberRepository.save(member);
        }

    }

    @Transactional
    @Override
    public void leave(MemberDto leavMemberDto) {
        // 이메일과 비밀번호 일치 시
        Member member = memberRepository.findByEmail(leavMemberDto.getEmail()).get();
        if (!passwordEncoder.matches(leavMemberDto.getPassword(), member.getPassword())) {
            throw new IllegalStateException("현재 비밀번호가 다릅니다.");
        } else {
            reviewRepository.deleteByMember(member);
            memberRepository.delete(member);
        }
    }

}
