package com.example.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {

    PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

    // [Movie(mno=196, title=Movie96), MovieImage(inum=563,
    // uuid=22fed75a-db21-4673-a75d-0bb4f2ece49b, imgName=img0.jpg, path=null), 2,
    // 4.0]
    // entity => dto
    public default MovieDto entityToDto(Movie movie, List<MovieImageDto> movieImages, Long ReviewCnt, double avg) {
        MovieDto dto = MovieDto.builder()
                .avg(avg)
                .reviewCnt(ReviewCnt)
                .createdDate(movie.getCreatedDate())
                .lastModifiedDate(movie.getLastModifiedDate())
                .mno(movie.getMno())
                .title(movie.getTitle())
                .build();

        // 영화 상세 조회 -> 이미지를 모두 조회
        List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
            return MovieImageDto.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());
        dto.setMovieImageDtos(movieImageDtos);
        return dto;
    }

    // dto => entity
    public default Movie dtoToEntity(MovieDto dto) {

        return Movie.builder().title(dto.getTitle()).mno(dto.getMno()).build();
    }
}
