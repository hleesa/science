package com.ftence.backend.repository;

import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void 저장() {

    }

    @Test
    void 수정() {

        // find or retrieve an entity by id
        Long id = 1L;
        Comment comment = commentRepository.findById(id).get();

        // update entity information
        comment.setContent("누수 조심하세요. 그리고 뒤에서 계속 쓰입니다.");

        // save updated entity
        commentRepository.save(comment);
    }

    @Test
    void findById_메서드() {
        Long id = 1L;

        Comment comment = commentRepository.findById(id).get();
        System.out.println(comment.toString());
    }

    @Test
    void saveAll_메서드() {
        List<User> wonlim = userRepository.findByIntraId("wonlim");
        List<User> sooyang = userRepository.findByIntraId("sooyang");

        Subject ft_printf = subjectRepository.findByName("ft_printf");
        Subject gnl = subjectRepository.findByName("get_next_line");


    }

    @Test
    void findAll_메서드() {
        List<Comment> comments = commentRepository.findAll();

        comments.forEach((c) -> {
            System.out.println(c.toString());
        });
    }

    @Test
    void deleteById_메서드() {
        Long id = 3L;
        commentRepository.deleteById(id);
    }

    @Test
    void delete_메서드() {
        // find an entity by id
        Long id = 2L;
        Comment comment = commentRepository.findById(id).get();

        // delete(entity)
        commentRepository.delete(comment);
    }

    @Test
    void deleteAll_메서드() {

//        userRepository.deleteAll();

        Comment comment1 = commentRepository.findById(6L).get();
        Comment comment2 = commentRepository.findById(7L).get();

        commentRepository.deleteAll(List.of(comment1, comment2));
    }

    @Test
    void count_메서드() {
        long count = commentRepository.count();
        System.out.println(count);
    }

    @Test
    void existsById_메서드() {
        Long id = 10L;

        boolean result = commentRepository.existsById(id);
        System.out.println(result);
    }
}
