package com.example.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto requestDto) {

        Page<Object[]> result = boardRepository.list(requestDto.getType(), requestDto.getKeyword(),
                requestDto.getPageable(Sort.by("bno").descending()));

        Function<Object[], BoardDto> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                (Long) entity[2]));

        // return objectList.stream().map(fn).collect(Collectors.toList());
        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto getRow(Long bno) {
        Object[] objectList = boardRepository.getrow(bno);

        return entityToDto((Board) objectList[0], (Member) objectList[1], (Long) objectList[2]);

    }

    @Override
    public Long modify(BoardDto dto) {
        Board board = boardRepository.findById(dto.getBno()).get();

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        Board newBoard = boardRepository.save(board);

        return newBoard.getBno();
    }

    @Transactional
    @Override
    public void deleteWithReply(Long bno) {
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);

    }

    @Override
    public Long createBoard(BoardDto dto) {

        Optional<Member> member = memberRepository.findById(dto.getWriterEmail());
        if (member.isPresent()) {
            Board board = Board.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .writer(member.get())
                    .build();
            boardRepository.save(board);
            return board.getBno();
        }

        return null;

    }

}
