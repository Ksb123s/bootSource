package com.example.movie.repository;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.constant.MemberRole;
import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.Review;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImgRepository movieImgRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMovieTest() {
        // 영화 + 영화 이미지
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie" + i)
                    .build();
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("img" + j + ".jpg")
                        .build();
                movieImgRepository.save(movieImage);

            }
        });
    }

    @Test
    public void insertMemberTest() {
        // 영화 + 영화 이미지
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("mem" + i + "@naver.com")
                    .password(passwordEncoder.encode("1111"))
                    .memberRole(MemberRole.MEMBER)
                    .nickname("reviewer" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void insertReviewTest() {
        // 리뷰 셈플 데이터
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long) (Math.random() * 100) + 99;
            System.out.println(mno);
            Movie movie = Movie.builder().mno(mno)
                    .build();

            Long mid = (long) (Math.random() * 100);
            System.out.println(mid);
            Member member = Member.builder().mid(mid).build();
            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 영화에 대한 ... " + i)
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    public void movieListTest() {

        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Object[]> list = movieRepository.getListPage(pageRequest);
        // Page<Object[]> list2 = movieRepository.getListPage2(pageRequest);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void movieImageListTest() {

        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Object[]> list = movieImgRepository.getTotalList(pageRequest);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
