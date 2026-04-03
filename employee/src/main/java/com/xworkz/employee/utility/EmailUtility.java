package com.xworkz.employee.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtility {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sn389854@gmail.com");
        message.setTo(toEmail);//dynamic email
        message.setSubject("Your OTP Password Reset");
        message.setText(
                "Hello,\n\n" + "Your OTP for password reset is: " + otp + "\n\n");
        mailSender.send(message);
        System.out.println("OTP email sent to: " + toEmail);
    }
}