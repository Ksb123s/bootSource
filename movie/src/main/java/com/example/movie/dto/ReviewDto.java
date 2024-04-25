package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDto {

    private Long reviewNo;

    private int grade;

    private String text;

    // Basic Entity
    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    // member 관계
    private Long mid;

    private String nickname;

    private String email;

    // movie 관계
    private Long mno;
}
