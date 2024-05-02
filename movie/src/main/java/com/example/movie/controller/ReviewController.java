package com.example.movie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;
import com.example.movie.service.MovieService;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDto>> getReviewList(@PathVariable("mno") Long mno) {

        return new ResponseEntity<>(service.getListOfMovie(mno), HttpStatus.OK);
    }

    //
    @PostMapping("/{mno}")
    public ResponseEntity<Long> postReview(@RequestBody ReviewDto reviewDto) {

        return new ResponseEntity<Long>(service.addReview(reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewNo}")
    public ResponseEntity<Long> remove(@PathVariable("reviewNo") Long reviewNo) {

        service.removeReview(reviewNo);
        return new ResponseEntity<Long>(reviewNo, HttpStatus.OK);
    }

    @GetMapping("/{mno}/{reviewNo}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable("reviewNo") Long reviewNo) {
        return new ResponseEntity<ReviewDto>(service.getReview(reviewNo), HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewNo}")
    public ResponseEntity<Long> putUpdateReview(@PathVariable("reviewNo") Long reviewNo,
            @RequestBody ReviewDto reviewDto) {

        return new ResponseEntity<Long>(service.updateReview(reviewDto), HttpStatus.OK);
    }

}
