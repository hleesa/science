package com.ftence.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubjectListServiceTest {

    @Autowired
    SubjectService subjectListService;

    @Test
    void 평균을_넣어야_해(){
        subjectListService.getSubjectList();
    }
}