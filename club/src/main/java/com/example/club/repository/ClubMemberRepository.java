package com.example.club.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.club.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    // 회원 찾기(email, social 회원 여부)
    @EntityGraph(attributePaths = { "rolSet" }, type = EntityGraphType.LOAD)
    @Query("SELECT m FROM ClubMember m WHERE m.email = :email And m.fromSocial = :social")
    Optional<ClubMember> findByEmailAndFromSocial(String email, boolean social);

}
