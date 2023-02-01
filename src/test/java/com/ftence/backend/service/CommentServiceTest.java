package com.ftence.backend.service;

import com.ftence.backend.dto.Response.CommentResponseDTO;
import com.ftence.backend.entity.Comment;
import com.ftence.backend.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test(){
        Comment comment = commentRepository.findById(4L).get();
        CommentResponseDTO commentResponseDTO = commentService.convertEntityToCommentResponseDto(comment);
        System.out.println(commentResponseDTO);
    }
}