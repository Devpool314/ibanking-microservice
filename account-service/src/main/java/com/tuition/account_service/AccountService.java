package com.tuition.account_service;

import org.springframework.stereotype.Service;
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
}