package com.tuition.account_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock; // Thêm import này
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.persistence.LockModeType; // Thêm import này
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByUserId(Long userId);

    // --- PHẦN THÊM MỚI ---
    /**
     * Lấy và KHÓA một hàng trong bảng Account.
     * Nó sẽ dịch thành câu lệnh SQL "SELECT ... FOR UPDATE",
     * ngăn chặn các giao dịch khác đọc hoặc ghi vào hàng này.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.userId = :userId")
    Optional<Account> findByUserIdForUpdate(Long userId);
}