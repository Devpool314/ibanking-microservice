package com.tuition.account_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Chúng ta sẽ thêm hàm @Lock vào đây ở Giai đoạn 3
    Optional<Account> findByUserId(Long userId);
}