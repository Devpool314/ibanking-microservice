package com.tuition.payment_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("student-service")
public interface StudentServiceClient {

    @PostMapping("/api/v1/students/clear-debt")
    ResponseEntity<String> clearDebt(@RequestBody ClearDebtRequest clearDebtRequest);
}