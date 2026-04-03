package com.xworkz.employee.utility;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtility {

    public String generateOtp() {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        System.out.println("Generated OTP: " + otp);
        return otp;
    }
}
