package com.example.goaltrail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goaltrail.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
