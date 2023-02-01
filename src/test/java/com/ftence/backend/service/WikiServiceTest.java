package com.ftence.backend.service;

import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.Wiki;
import com.ftence.backend.repository.SubjectRepository;
import com.ftence.backend.repository.WikiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WikiServiceTest {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private WikiRepository wikiRepository;
    @Test
    void 버전_1로_초기화(){

        Subject libft = subjectRepository.findById(1L).get();
        Subject gnl = subjectRepository.findById(2L).get();
        Subject printf = subjectRepository.findById(3L).get();
        Subject b2b = subjectRepository.findById(4L).get();

        Wiki wiki1 = new Wiki(null, 0L, libft, "", "wonlim",null);
        Wiki wiki2 = new Wiki(null, 0L, gnl, "", "wonlim",null);
        Wiki wiki3 = new Wiki(null, 0L, printf, "", "wonlim",null);
        Wiki wiki4 = new Wiki(null, 0L, b2b, "", "wonlim",null);

        wikiRepository.save(wiki1);
        wikiRepository.save(wiki2);
        wikiRepository.save(wiki3);
        wikiRepository.save(wiki4);
    }
}