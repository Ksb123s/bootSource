package com.example.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.entity.BookEntity;
import com.example.book.entity.CartegoryEntity;
import com.example.book.entity.PublisherEntity;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CartegoryRepository;
import com.example.book.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CartegoryRepository cartegoryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public List<BookDto> getList() {
        List<BookEntity> books = bookRepository.findAll(Sort.by("id").descending());

        // List<BookDto> bookDtos = new ArrayList<>();

        // books.forEach(book ->{
        // bookDtos.add(entityToDto(book));
        // });
        List<BookDto> bookDtos = books.stream().map(book -> entityToDto(book)).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public Long bookCreate(BookDto dto) {
        BookEntity book = dtoToEntity(dto);
        CartegoryEntity cartegory = cartegoryRepository.findByName(dto.getCartegoryName()).get();
        PublisherEntity publisher = publisherRepository.findByName(dto.getPublisherName()).get();
        book.setCartegory(cartegory);
        book.setPublisher(publisher);
        BookEntity newbook = bookRepository.save(book);
        return newbook.getId();

    }

    @Override
    public List<String> categoryNameList() {
        List<CartegoryEntity> categorys = cartegoryRepository.findAll();

        return categorys.stream().map(i -> i.getName()).collect(Collectors.toList());
    }

    @Override
    public List<String> pulisherNameList() {
        List<PublisherEntity> publishers = publisherRepository.findAll();
        return publishers.stream().map(i -> i.getName()).collect(Collectors.toList());
    }

    @Override
    public BookDto getRow(Long id) {
        BookEntity entity = bookRepository.findById(id).get();

        return entityToDto(entity);
    }

    @Override
    public Long bookUpate(BookDto dto) {
        BookEntity entity = bookRepository.findById(dto.getId()).get();
        entity.setPrice(dto.getPrice());
        entity.setSalePrice(dto.getSalePrice());

        BookEntity newEntity = bookRepository.save(entity);

        return newEntity.getId();

    }

    @Override
    public void bookDelete(Long id) {
        BookEntity entity = bookRepository.findById(id).get();

        bookRepository.delete(entity);
    }

}
