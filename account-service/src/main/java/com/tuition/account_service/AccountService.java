package com.tuition.account_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // Lấy số dư
    public Double getBalance(Long userId) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản cho User ID: " + userId));
        return account.getBalance();
    }

    // Lấy lịch sử giao dịch
    public List<Transaction> getHistory(Long userId) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản cho User ID: " + userId));
        
        return transactionRepository.findByAccountIdOrderByTimestampDesc(account.getId());
    }

    @Transactional
    public Account debitAccount(Long userId, Double amount) {
        // 1. Lấy và KHÓA tài khoản ngay lập tức
        Account account = accountRepository.findByUserIdForUpdate(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản cho User ID: " + userId));

        // 2. Kiểm tra số dư (Yêu cầu: "học phí phải nhỏ hơn số dư")
        if (account.getBalance() < amount) {
            throw new RuntimeException("Không đủ số dư để thực hiện giao dịch.");
        }

        // 3. Trừ tiền
        account.setBalance(account.getBalance() - amount);
        
        // 4. Lưu lại (và nhả lock khi @Transactional kết thúc)
        return accountRepository.save(account);
    }
}