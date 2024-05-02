package com.example.movie.service;

import java.util.List;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewService {

    // 특정 영화의 모든 리뷰 가져오기
    List<ReviewDto> getListOfMovie(Long mno);

    // 특정 영화의 리뷰 등록
    Long addReview(ReviewDto reviewDto);

    void removeReview(Long reviewNo);

    ReviewDto getReview(Long reviewNo);

    Long updateReview(ReviewDto reviewDto);

    public default ReviewDto entityToDto(Review review) {

        return ReviewDto.builder()
                .reviewNo(review.getReviewNo())
                .grade(review.getGrade())
                .text(review.getText())
                .createdDate(review.getCreatedDate())
                .lastModifiedDate(review.getLastModifiedDate())
                .mid(review.getMember().getMid())
                .nickname(review.getMember().getNickname())
                .email(review.getMember().getEmail())
                .mno(review.getMovie().getMno())
                .build();

    }

    public default Review dtoToEntity(ReviewDto reviewdDto) {
        Movie movie = Movie.builder().mno(reviewdDto.getMno()).build();
        Member member = Member.builder().mid(reviewdDto.getMid())
                .build();
        return Review.builder()
                .reviewNo(reviewdDto.getReviewNo())
                .grade(reviewdDto.getGrade())
                .text(reviewdDto.getText())
                .member(member)
                .movie(movie)
                .build();
    }

}
