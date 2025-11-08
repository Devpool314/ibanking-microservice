package com.tuition.account_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Tìm các giao dịch của 1 tài khoản, sắp xếp theo ngày mới nhất
    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}