package com.tuition.otp_service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtpRabbitMQConfig {

    public static final String OTP_EMAIL_QUEUE = "otp_email_queue";

    @Bean
    public Queue otpQueue() {
        return new Queue(OTP_EMAIL_QUEUE, false);
    }
}