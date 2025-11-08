package com.tuition.payment_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface AccountServiceClient {

    @PostMapping("/api/v1/accounts/debit")
    ResponseEntity<String> debit(@RequestBody DebitRequest debitRequest);
    
    // Chúng ta cần thêm API hoàn tiền cho Saga
    @PostMapping("/api/v1/accounts/refund")
    ResponseEntity<String> refund(@RequestBody DebitRequest debitRequest);
}