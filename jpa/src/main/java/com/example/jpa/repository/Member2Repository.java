package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Member2;
import com.example.jpa.entity.Team2;

public interface Member2Repository extends JpaRepository<Member2, Long> {

    // jpql 사용 시
    // 1. entity 타입 결과 => Lsit<entity>
    // 2. 개별 타입 결과 => Lsit<Object[]>
    @Query("SELECT m FROM Member2 m")
    List<Member2> findbyMembers(Sort sort);

    @Query("SELECT m.userName, m.age FROM Member2 m")
    List<Object[]> findbyMembers2();

    @Query("SELECT m FROM Member2 m WHERE m.age >= ?1")
    List<Member2> findByAgeList(int age);

    // 특정 팀의 회우너 조회;
    @Query("SELECT m FROM Member2 m WHERE m.team2 = ?1")
    List<Member2> findByTeamEqual(Team2 team2);

    @Query("SELECT m FROM Member2 m WHERE m.team2.id = ?1")
    List<Member2> findByTeamEqual(Long id);

    @Query("SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) FROM Member2 m")
    List<Object[]> aggregate();

    @Query("SELECT m FROM Member2 m JOIN m.team2 t WHERE t.name = :TeamName")
    List<Member2> findByTeamMember(String TeamName);

    @Query("SELECT m,t FROM Member2 m JOIN m.team2 t WHERE t.name = :TeamName")
    List<Object[]> findByTeamMember2(String TeamName);

    // 외부조인
    @Query("SELECT m,t FROM Member2 m LEFT JOIN m.team2 t ON t.name = :TeamName")
    List<Object[]> findByTeamMember3(String TeamName);
}
