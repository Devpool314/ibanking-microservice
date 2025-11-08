package com.tuition.otp_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OtpService {

    // Giả sử bạn dùng Redis (đã thêm Spring Data Redis)
    private final RedisTemplate<String, String> redisTemplate;
    private final RabbitTemplate rabbitTemplate;

    private static final long OTP_EXPIRATION_MINUTES = 5; // 5 phút

    /**
     * Tạo OTP, lưu vào Redis, và gửi lệnh gửi mail.
     */
    public String generateOtp(String email) {
        String otp = new Random().nextInt(999999) + ""; // Tạo 6 số
        
        // Lưu vào Redis: Key là email, Value là OTP
        redisTemplate.opsForValue().set(email, otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);

        // Gửi lệnh gửi mail (Yêu cầu 5)
        // Một message đơn giản, bạn có thể tạo DTO phức tạp hơn
        String message = email + ":" + otp;
        rabbitTemplate.convertAndSend(OtpRabbitMQConfig.OTP_EMAIL_QUEUE, message);
        
        return otp; // (Chỉ trả về để test, thực tế không nên trả OTP qua API)
    }

    /**
     * Xác thực OTP (Yêu cầu 6)
     */
    public boolean validateOtp(String email, String otpToValidate) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        
        if (storedOtp != null && storedOtp.equals(otpToValidate)) {
            redisTemplate.delete(email); // Xóa OTP sau khi dùng
            return true;
        }
        return false;
    }
}