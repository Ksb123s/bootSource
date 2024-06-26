package com.example.board.controllor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDto;
import com.example.board.repository.ReplyRepository;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RequestMapping("/replies")
@Log4j2
@RestController
public class ReplyControllor {

    private final ReplyService service;

    // test http://localhost:8080/replies/board/91
    @GetMapping("board/{bno}")
    public List<ReplyDto> getListByBoard(@PathVariable("bno") Long bno) {
        log.info("댓글 가져오기 {} ", bno);
        List<ReplyDto> replies = service.getReplies(bno);
        return replies;
    }

    // replies/new
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new")
    public ResponseEntity<Long> postMethodName(@RequestBody ReplyDto dto) {
        log.info("댓글 등록 {}", dto);

        return new ResponseEntity<Long>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> deleteReply(@PathVariable("rno") Long rno) {
        log.info("댓글 제거 {} ", rno);
        service.delete(rno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{rno}")
    public ResponseEntity<ReplyDto> getRow(@PathVariable("rno") Long rno) {
        return new ResponseEntity<ReplyDto>(service.getReply(rno), HttpStatus.OK);
    }

    @PreAuthorize("authentication.name == #dto.writerEmail")
    @PutMapping("/{rno}")
    public ResponseEntity<String> putMethodName(@PathVariable("rno") Long rno, @RequestBody ReplyDto dto) {

        log.info("reply 수정 요청 {}, {}", rno, dto);

        Long Rno = service.update(dto);
        return new ResponseEntity<String>(String.valueOf(rno), HttpStatus.OK);
    }

}
