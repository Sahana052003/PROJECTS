package com.xworkz.employee.service;

import com.xworkz.employee.dto.EmailDTO;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Component
public interface OtpService {
    public String sendMailWithDynamicHTMLBody(EmailDTO emailDTO) ;
    boolean generateAndSendOtp(String emailId);
    boolean verifyOtp(String emailId, String enteredOtp);
}