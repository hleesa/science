package com.ftence.backend.repository;

import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectRepositoryTest {
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void 저장() {
        // create subject
//        Subject libft = new Subject(null, 0, "Libft", 13, 20, 0L, "라이브러리를 만들어보는 과제입니다.", "naver.com");

        // save subject
//        Subject savedObject = subjectRepository.save(libft);

        // display user info
//        System.out.println(savedObject.getId());
//        System.out.println(savedObject.toString());
    }

    @Test
    void 수정() {

        // find or retrieve an entity by id
        Subject subject = subjectRepository.findById(4L).get();

        // update entity information
//        subject.setWiki();

        // save updated entity
//        subjectRepository.save(subject);
    }

    @Test
    void findById_메서드() {
        Long id = 1L;

        Subject subject = subjectRepository.findById(id).get();
    }

    @Test
    void saveAll_메서드() {
        // create new subject..
//        Subject gnl = new Subject(null, 0, "get_next_line", 30, 31, 0L,"파일에서 한 줄씩 읽어오는 함수를 만드는 과제이다.", "gnl.pdf");
//        Subject printf = new Subject(null, 0, "ft_printf", 20, 27, 0L,"printf 함수를 직접 만들어보는 과제이다.", "printf.pdf");
//        Subject bornToBeRoot = new Subject(null, 0, "BornToBeRoot", 30, 40, 0L,"가상화 환경을 공부해보자.", "born2be.pdf");


//        subjectRepository.saveAll(List.of(gnl, printf, bornToBeRoot));
    }

    @Test
    void findAll_메서드() {
        List<Subject> subjects = subjectRepository.findAll();

        subjects.forEach((u) -> {
            System.out.println(u.getName());
        });
    }

    @Test
    void deleteById_메서드() {
        Long id = 3L;
        subjectRepository.deleteById(id);
    }

    @Test
    void delete_메서드() {
        // find an entity by id
        Long id = 2L;
        Subject subject = subjectRepository.findById(id).get();

        // delete(entity)
        subjectRepository.delete(subject);
    }

    @Test
    void deleteAll_메서드() {

//        userRepository.deleteAll();

        Subject subject1 = subjectRepository.findById(6L).get();
        Subject subject2 = subjectRepository.findById(7L).get();

        subjectRepository.deleteAll(List.of(subject1, subject2));
    }

    @Test
    void count_메서드() {
        long count = subjectRepository.count();
        System.out.println(count);
    }

    @Test
    void existsById_메서드() {
        Long id = 10L;

        boolean result = subjectRepository.existsById(id);
        System.out.println(result);
    }

    @Test
    void wiki_version_수정(){
        Subject subject1 = subjectRepository.findById(1L).get();
        Subject subject2 = subjectRepository.findById(2L).get();
        Subject subject3 = subjectRepository.findById(3L).get();
        Subject subject4 = subjectRepository.findById(4L).get();

        subject1.setWikiVersion(1L);
        subject2.setWikiVersion(0L);
        subject3.setWikiVersion(0L);
        subject4.setWikiVersion(0L);

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        subjectRepository.save(subject3);
        subjectRepository.save(subject4);
    }

}