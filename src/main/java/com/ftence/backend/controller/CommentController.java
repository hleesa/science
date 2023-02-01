package com.ftence.backend.controller;

import com.ftence.backend.dto.Request.CommentRequestDTO;
import com.ftence.backend.dto.Response.CommentResponseDTO;
import com.ftence.backend.dto.Response.LoginResponseDTO;
import com.ftence.backend.repository.SubjectRepository;
import com.ftence.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class CommentController  {

    private final CommentService commentService;
    private final SubjectRepository subjectRepository;

    @GetMapping("/data")
    public List<LoginResponseDTO> getAllUsersComment(){
        return commentService.getAllUserComment();
    }

    @PostMapping("/subject/comment")
    public void saveCommentInfo(@RequestBody CommentRequestDTO commentRequestDTO){
        commentService.write(commentRequestDTO);
    }

    @GetMapping("/{subjectName}/comment")
    public List<CommentResponseDTO> getCommentList(@PathVariable String subjectName){
        return commentService.getAllSubjectComment(subjectRepository.findByName(subjectName));
    }
}
