package com.example.FlightBooking.Utils;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class OtpUtils {
    private final Set<String> otpSet = new HashSet<>();

    public String generateOtp() {
        String otp;
        do {
            otp = generateRandomOtp();
        } while (!otpSet.add(otp)); // Kiểm tra xem mã OTP đã tồn tại hay chưa
        return otp;
    }

    private String generateRandomOtp() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999
        return String.valueOf(randomNumber);
    }
}

