package com.example.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;

import com.example.book.dto.BookDto;
import com.example.book.entity.BookEntity;
import com.example.book.entity.CartegoryEntity;
import com.example.book.entity.PublisherEntity;
import com.example.book.repository.CartegoryRepository;

public interface BookService {
    List<BookDto> getList();

    Long bookCreate(BookDto dto);

    List<String> categoryNameList();

    List<String> pulisherNameList();

    BookDto getRow(Long id);

    Long bookUpate(BookDto dto);

    void bookDelete(Long id);

    public default BookDto entityToDto(BookEntity book) {
        BookDto dto = BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .writer(book.getWriter())
                .createdDate(book.getCreateDate())
                .lastModifiedDate(book.getLastModifiedDate())
                .publisherName(book.getPublisher().getName())
                .cartegoryName(book.getCartegory().getName())
                .salePrice(book.getSalePrice())
                .price(book.getPrice())
                .build();

        return dto;

    }

    public default BookEntity dtoToEntity(BookDto dto) {

        BookEntity entity = BookEntity.builder()
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .price(dto.getPrice())
                .salePrice(dto.getSalePrice())
                .build();

        return entity;
    }

}
