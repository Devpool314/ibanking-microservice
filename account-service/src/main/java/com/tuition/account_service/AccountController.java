package com.tuition.account_service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // API 1: GET http://localhost:8082/api/v1/accounts/1/balance
    @GetMapping("/{userId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getBalance(userId));
    }

    // API 2: GET http://localhost:8082/api/v1/accounts/1/history
    @GetMapping("/{userId}/history")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getHistory(userId));
    }

    @PostMapping("/debit")
    public ResponseEntity<Account> debit(@RequestBody DebitRequest debitRequest) {
        Account updatedAccount = accountService.debitAccount(
            debitRequest.getUserId(), 
            debitRequest.getAmount()
        );
        return ResponseEntity.ok(updatedAccount);
    }
}

class DebitRequest {
    private Long userId;
    private Double amount;

    // Thêm getters & setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}