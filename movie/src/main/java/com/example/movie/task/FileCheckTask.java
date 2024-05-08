package com.example.movie.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.movie.dto.MovieImageDto;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileCheckTask {

    private MovieImageRepository movieImageRepository;

    @Value("${com.example.upload.path}")
    private String uploadPath;

    // 전일자 폴더 구하기
    private String getFolderYesterday() {
        // 어제 날자 추출
        LocalDate yesterDay = LocalDate.now().minusDays(1);
        String yesterday = yesterDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 2024\05\07
        return yesterday.replace("-", File.separator);
    }

    // 리눅스에서 사용 되던 프로그램
    // ( * * * * *) 분 시 일 월 연도 /0은 초
    @Scheduled(cron = "0 * * * * *")
    public void CheckFiles() {
        log.info("file check task 시작 ....");

        // 데이터 베이스상 어제 날짜 이미지 파일 리스트
        List<MovieImage> oldImages = movieImageRepository.getOldMovieImages();

        //
        List<MovieImageDto> movieImageDtos = oldImages.stream().map(movieImage -> {
            return MovieImageDto.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());
        // dto 대용 수집 ==> 이미지 파일 한개 당 c:\\upload\\2024
        List<Path> fileListPath = movieImageDtos.stream()
                .map(dto -> Paths.get(uploadPath, dto.getImageURL(), dto.getUuid() + "_" + dto.getImgName()))
                .collect(Collectors.toList());

        movieImageDtos.stream()
                .map(dto -> Paths.get(uploadPath, dto.getImageURL(), dto.getUuid() + "s_" + dto.getImgName()))
                .forEach(p -> fileListPath.add(p));
        // for (Path path : fileListPath) {
        // log.info(path);
        // }

        File targetDir = Paths.get(uploadPath, getFolderYesterday()).toFile();
        // targetDir 안의 파일과 DB 파일 목록 비교 후 일치하지 않는 파일 목록 생성

        File[] removFiles = targetDir.listFiles(f -> fileListPath.contains(f.toPath()) == false);

        if (removFiles != null) {
            for (File file : removFiles) {
                file.delete();
            }
        }
    }

}
