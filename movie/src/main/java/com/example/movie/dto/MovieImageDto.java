package com.example.movie.dto;

import java.time.LocalDateTime;

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
public class MovieImageDto {

    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    // member 관계
    private Long mid;

    private String nickname;

    private String email;
}
