package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

public interface ReplyService {

    List<ReplyDto> getReplies(Long bno);

    Long create(ReplyDto dto);

    void delete(Long rno);

    ReplyDto getReply(Long rno);

    Long update(ReplyDto dto);

    public default ReplyDto entityToDto(Reply reply) {

        return ReplyDto.builder()
                .bno(reply.getBoard().getBno())
                .text(reply.getText())
                .rno(reply.getRno())
                .writerEmail(reply.getReplyer().getEmail())
                .writerName(reply.getReplyer().getName())
                .createDate(reply.getCreatedDate())
                .lastModifiedDate(reply.getLastModifiedDate())
                .build();
    }

    // dto => entity
    public default Reply dtoToEntity(ReplyDto dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board board = Board.builder().bno(dto.getBno()).build();
        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(member)
                .board(board)
                .build();
    }

}
