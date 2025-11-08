package com.tuition.student_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TuitionDebtRepository extends JpaRepository<TuitionDebt, Long> {
    // Tìm khoản nợ bằng mssv
    Optional<TuitionDebt> findByMssv(String mssv);
}