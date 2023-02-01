package com.ftence.backend.repository;

import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySubject(Subject subject);
}
