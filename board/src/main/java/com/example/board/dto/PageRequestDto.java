package com.example.board.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {

    private int page;
    private int size;

    private String type;
    private String keyword;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
        this.keyword = "";
        this.type = "";
    }

    // 스프링 페이지 나누기 정보 저장 객체 => pageable
    // 1 page => 0 부터 시작
    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page - 1, size, sort);

    }

}
