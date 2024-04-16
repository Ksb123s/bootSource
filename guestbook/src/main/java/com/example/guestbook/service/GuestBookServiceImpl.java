package com.example.guestbook.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.example.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // @Override
    // public List<GuestBookDto> getList() {
    // List<GuestBook> entites = guestBookRepository.findAll();

    // Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
    // return entites.stream().map(fn).collect(Collectors.toList());
    // }

    @Override
    public GuestBookDto rowRead(Long gno) {
        GuestBook guestBook = guestBookRepository.findById(gno).get();

        GuestBookDto dto = entityToDto(guestBook);

        return dto;
    }

    @Override
    public Long modify(GuestBookDto dto) {
        GuestBook guestBook = guestBookRepository.findById(dto.getGno()).get();
        guestBook.setContent(dto.getContent());
        guestBook.setTitle(dto.getTitle());
        GuestBook newGuestBook = guestBookRepository.save(guestBook);
        return newGuestBook.getGno();
    }

    @Override
    public void Delete(Long gno) {
        guestBookRepository.deleteById(gno);

    }

    @Override
    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        // Page<GuestBook> result = guestBookRepository.findAll(pageable);
        // PagingAndSortingRepository.findAll()
        BooleanBuilder builder = getSearch(requestDto);
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);
        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<GuestBookDto, GuestBook>(result, fn);
    }

    @Override
    public Long create(GuestBookDto dto) {
        GuestBook guestBook = dtoToEntity(dto);

        GuestBook newguestBook = guestBookRepository.save(guestBook);
        return newguestBook.getGno();
    }

    // Book(다른) 프로젝트에서는 BookRepository 에서 작성 함
    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        //
        BooleanBuilder builder = new BooleanBuilder();

        // Q 클래스 사용
        QGuestBook guestBook = QGuestBook.guestBook;

        // type 가져오기
        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();

        // where gno > 0 AND title like %title% or content like %content%
        // gno> 0
        builder.and(guestBook.gno.gt(0L));

        if (type == null || type.trim().length() == 0) {
            return builder;
        }
        // 검색 타입 존재
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("t")) {
            conditionBuilder.or(guestBook.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(guestBook.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(guestBook.writer.contains(keyword));
        }
        builder.and(conditionBuilder);

        return builder;
    }
}
