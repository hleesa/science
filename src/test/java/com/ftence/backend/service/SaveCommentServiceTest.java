package com.ftence.backend.service;

import com.ftence.backend.dto.Request.CommentRequestDTO;
import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import com.ftence.backend.repository.CommentRepository;
import com.ftence.backend.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaveCommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void 값_저장(){

        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setIntraId("sooyang");
        commentRequestDTO.setContent("맨대터리는 쉬워요. 근데 보너스를 하려면 코드를 새로 짜야 합니다.");
        commentRequestDTO.setStarRating(3);
        commentRequestDTO.setTimeTaken("a_week");
        commentRequestDTO.setAmountStudy("low");
        commentRequestDTO.setBonus("no");
        commentRequestDTO.setDifficulty("normal");
        commentRequestDTO.setSubjectName("ft_printf");

        commentService.write(commentRequestDTO);
    }

    @Test
    void 값_수정(){
        Comment comment = commentRepository.findById(2L).get();
        Subject  subject = subjectRepository.findByName("ft_printf");

        comment.setSubject(subject);

        commentRepository.save(comment);
    }
}