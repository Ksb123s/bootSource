package com.example.board.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class BoardDto {
    private Long bno;

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "작성자를 입력하세요")
    private String content;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "작성자 아이디를 입력하세요")
    private String writerEmail; // 작성자 아이디

    private String writerName; // 작성자 이름

    private Long replyCount; // 해당 게시물의 댓글 수

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;
}
