package com.example.jpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // id 로 찾기
    @Query(value = "SELECT * FROM board", nativeQuery = true)
    List<Board> findList();

    // title 로 찾기
    @Query("SELECT b From Board b WHERE b.title LIKE ?1%")
    List<Board> findByTitle(String title);

    // like title
    List<Board> findByTitleLike(String title);

    // StartingWith title
    List<Board> findByTitleStartingWith(String title);

    // EndingWith title
    List<Board> findByTitleEndingWith(String title);

    // Containing title
    List<Board> findByTitleContaining(String title);

    // writter 로 찾기
    @Query("SELECT b From Board b WHERE b.writer LIKE %:writer%")
    List<Board> findByWriter(String writer);

    // writer가 user로 시작
    List<Board> findByWriterStartingWith(String writer);

    // title 이 Title문자열이 포함도어 있거나
    // content 이 Content 포함되어 있는 데이터
    @Query("SELECT b From Board b WHERE b.title LIKE %:title% OR b.content LIKE %:content%")
    List<Board> findByTitleContainingOrContentContaining(String title, String content);

    @Query("SELECT b From Board b WHERE b.title LIKE %:title% OR b.content = :content")
    List<Board> findByTitleContainingOrContent(String title, String content);

    // title 이 Title 이 있고 id 가 50 보다큰
    @Query("SELECT b From Board b WHERE b.title LIKE %?1% OR b.id > ?2")
    List<Board> findByTitleContainingAndIdGreaterThan(String title, Long id);

    // id 가 50 보다크고 내림차순
    List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

    List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);
}
