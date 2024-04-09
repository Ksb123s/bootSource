package com.example.book.dto;

import java.time.LocalDateTime;

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

    private String title;

    private String writer;

    private String publisher;

    private String cartegory;

    private Integer price;

    private Integer salePrice;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
