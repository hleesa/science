package com.ftence.backend.repository;

import com.ftence.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIntraId(String intraId);

    Optional<User> findUserByIntraId(String intraId);
}
