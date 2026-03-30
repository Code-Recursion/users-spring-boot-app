package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByFirstName(String fName);

    @Query("SELECT u FROM UserEntity u WHERE u.active = true")
    List<UserEntity> findActiveUsers();
}