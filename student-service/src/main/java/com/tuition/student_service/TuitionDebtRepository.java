package com.tuition.student_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.persistence.LockModeType;

import java.util.Optional;

@Repository
public interface TuitionDebtRepository extends JpaRepository<TuitionDebt, Long> {
    // Tìm khoản nợ bằng mssv
    Optional<TuitionDebt> findByMssv(String mssv);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM TuitionDebt d WHERE d.mssv = :mssv")
    Optional<TuitionDebt> findByMssvForUpdate(String mssv);
}