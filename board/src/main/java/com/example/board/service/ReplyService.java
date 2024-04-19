package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyService {

    List<ReplyDto> getReplies(Long bno);

    Long create(ReplyDto dto);

    void delete(Long rno);

    ReplyDto getReply(Long rno);

    public default ReplyDto entityToDto(Reply reply) {

        return ReplyDto.builder()
                .bno(reply.getBoard().getBno())
                .text(reply.getText())
                .rno(reply.getRno())
                .replyer(reply.getReplyer())
                .createDate(reply.getCreatedDate())
                .lastModifiedDate(reply.getLastModifiedDate())
                .build();
    }

    // dto => entity
    public default Reply dtoToEntity(ReplyDto dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
    }

}
