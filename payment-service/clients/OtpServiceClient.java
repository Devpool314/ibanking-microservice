package com.tuition.payment_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// "otp-service" là spring.application.name của service kia
@FeignClient("otp-service")
public interface OtpServiceClient {

    @PostMapping("/api/v1/otp/generate")
    ResponseEntity<String> generateOtp(@RequestBody OtpGenerationRequest request);

    @PostMapping("/api/v1/otp/validate")
    ResponseEntity<Boolean> validateOtp(@RequestBody OtpValidationRequest request);
}