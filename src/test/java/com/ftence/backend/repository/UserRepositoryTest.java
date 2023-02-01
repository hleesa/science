package com.ftence.backend.repository;

import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 저장() {
        // create user
        User user = new User(null, "wo", 3.9, null, "1111");

        // save user
        User savedObject = userRepository.save(user);

        // display user info
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());
    }

    @Test
    void 수정() {

        // find or retrieve an entity by id
        Long id = 1L;
        User user = userRepository.findById(id).get();

        // update entity information
        user.setLevel(3.2);

        // save updated entity
        userRepository.save(user);
    }

    @Test
    void findById_메서드() {
        Long id = 1L;

        User user = userRepository.findById(id).get();
    }

    @Test
    void saveAll_메서드() {
        User user = new User(null, "wonlim", 1.5, null, "1111");
        User user2 = new User(null, "sooyang", 2, null, "2222");
        User user3 = new User(null, "him", 4.5, null, "3333");
        User user4 = new User(null, "salee2", 8.5,  null, "4444");

        userRepository.saveAll(List.of(user, user2, user3, user4));
    }

    @Test
    void findAll_메서드() {
        List<User> users = userRepository.findAll();

        users.forEach((u) -> {
            System.out.println(u.getIntraId());
        });
    }

    @Test
    void deleteById_메서드() {
        Long id = 3L;
        userRepository.deleteById(id);
    }

    @Test
    void delete_메서드() {
        // find an entity by id
        Long id = 2L;
        User user = userRepository.findById(id).get();

        // delete(entity)
        userRepository.delete(user);
    }

    @Test
    void deleteAll_메서드() {

//        userRepository.deleteAll();

        User user = userRepository.findById(6L).get();
        User user2 = userRepository.findById(7L).get();

        userRepository.deleteAll(List.of(user, user2));
    }

    @Test
    void count_메서드() {
        long count = userRepository.count();
        System.out.println(count);
    }

    @Test
    void existsById_메서드() {
        Long id = 10L;

        boolean result = userRepository.existsById(id);
        System.out.println(result);
    }

    @Test
    void 인트라ID로_검색() {
        String intraId = "wonlim";

        List<User> user = userRepository.findByIntraId(intraId);
        System.out.println(user.toString());
    }
}

