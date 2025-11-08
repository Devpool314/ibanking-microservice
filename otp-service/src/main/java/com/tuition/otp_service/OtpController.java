package com.tuition.otp_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    // API để user/frontend gọi khi nhấn "Xác nhận giao dịch"
    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody OtpGenerationRequest request) {
        // (Trong thực tế, bạn lấy email từ user ID hoặc context bảo mật)
        otpService.generateOtp(request.getEmail());
        return ResponseEntity.ok("OTP đã được gửi đến " + request.getEmail());
    }

    // API nội bộ để payment-service gọi vào xác thực
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody OtpValidationRequest request) {
        boolean isValid = otpService.validateOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(isValid);
    }
}

// --- TẠO CÁC DTOs (trong file riêng hoặc chung) ---
class OtpGenerationRequest {
    private String email;
    // getter/setter
    public String getEmail() { return email; }
}

class OtpValidationRequest {
    private String email;
    private String otp;
    // getters/setters
    public String getEmail() { return email; }
    public String getOtp() { return otp; }
}