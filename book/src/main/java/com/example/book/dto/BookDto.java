package com.example.book.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    private Long id;

    @NotBlank(message = "제목은 필수 요소 입니다.")
    private String title;

    @NotBlank(message = "저자는 필수 요소 입니다.")
    private String writer;

    @NotBlank(message = "출판사 필수 요소 입니다.")
    private String publisherName;

    @NotBlank(message = "분류는 필수 요소 입니다.")
    private String cartegoryName;

    @NotNull(message = "가격은 필수 요소 입니다.")
    private Integer price;

    @NotNull(message = "할인가는 필수 요소 입니다.")
    private Integer salePrice;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
