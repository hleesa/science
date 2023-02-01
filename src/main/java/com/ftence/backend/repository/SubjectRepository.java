package com.ftence.backend.repository;

import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
