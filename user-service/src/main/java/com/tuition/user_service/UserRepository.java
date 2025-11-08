package com.tuition.user_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tự động có các hàm findById, save, delete...
    // Thêm hàm tìm bằng username
    Optional<User> findByUsername(String username);
}