package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReplyDto {

    private Long rno;

    private String text;

    private String writerEmail; // 작성자 아이디

    private String writerName; // 작성자 이름

    private Long bno;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;
}
