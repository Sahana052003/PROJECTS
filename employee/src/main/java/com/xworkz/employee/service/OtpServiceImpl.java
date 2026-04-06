package com.xworkz.employee.service;

import com.xworkz.employee.dao.EmployeeDAO;
import com.xworkz.employee.dao.OtpDAO;
import com.xworkz.employee.dto.EmailDTO;
import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.utility.EmailUtility;
import com.xworkz.employee.utility.OtpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService{
@Autowired
    private OtpDAO otpDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private OtpUtility otpUtility;

    @Autowired
    private EmailUtility emailUtility;

    @Override
    public String sendMailWithDynamicHTMLBody(EmailDTO emailDTO){
        return "";
    }

    @Override
    public boolean generateAndSendOtp(String emailId) {
        System.out.println("generateAndSendOtp called for: " + emailId);

        EmployeeEntity entity = employeeDAO.findByEmail(emailId);
        if (entity == null) {
            System.out.println("Email not found: " + emailId);
            return false;
        }

        // Generate OTP
        String otp = otpUtility.generateOtp();

        // Save OTP to DB — stays until password reset
        boolean saved = otpDAO.saveOtp(emailId, otp);
        if (!saved) {
            System.out.println("Failed to save OTP in DB");
            return false;
        }

        // Send email
        emailUtility.sendOtpEmail(emailId, otp);
        return true;
    }

    @Override
    public boolean verifyOtp(String emailId, String enteredOtp) {
        System.out.println("verifyOtp called for: " + emailId);

        if (enteredOtp == null || enteredOtp.trim().isEmpty()) {
            System.out.println("OTP is blank");
            return false;
        }

        // Check expiry FIRST
        LocalDateTime expiry = otpDAO.getOtpExpiry(emailId);
        if (expiry == null || LocalDateTime.now().isAfter(expiry)) {
            System.out.println("OTP expired for: " + emailId);
            otpDAO.clearOtp(emailId);
            return false;
        }

        String storedOtp = otpDAO.getOtp(emailId);
        if (storedOtp == null || !storedOtp.equals(enteredOtp.trim())) {
            System.out.println("OTP mismatch!");
            return false;
        }

        otpDAO.clearOtp(emailId); // clear after success
        System.out.println("OTP matched!");
        return true;
    }
}
