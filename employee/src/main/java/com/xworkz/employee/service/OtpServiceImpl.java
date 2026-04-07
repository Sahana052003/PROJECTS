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
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpDAO otpDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private OtpUtility otpUtility;

    @Autowired
    private EmailUtility emailUtility;

    @Override
    public String sendMailWithDynamicHTMLBody(EmailDTO emailDTO) {
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

        String otp = otpUtility.generateOtp();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(20);

        System.out.println("Generated OTP: " + otp + " | Expiry: " + expiry);

        boolean saved = otpDAO.saveOtp(emailId, otp, expiry);
        if (!saved) {
            System.out.println("Failed to save OTP in DB");
            return false;
        }

        emailUtility.sendOtpEmail(emailId, otp);
        System.out.println("OTP email sent to: " + emailId);
        return true;
    }

    @Override
    public boolean verifyOtp(String emailId, String enteredOtp) {
        System.out.println("verifyOtp called for: " + emailId
                + " | Entered: " + enteredOtp);

        // Step 1: get stored OTP and expiry from DB
        String storedOtp = otpDAO.getOtp(emailId);
        LocalDateTime expiry = otpDAO.getOtpExpiry(emailId);

        System.out.println("Stored OTP: " + storedOtp + " | Expiry: " + expiry);

        // Step 2: check if OTP is default (already used or never set)
        if (storedOtp == null || storedOtp.equals("0")) {
            System.out.println("No active OTP found for: " + emailId);
            return false;
        }

        // Step 3: check expiry in service
        if (expiry == null || LocalDateTime.now().isAfter(expiry)) {
            System.out.println("OTP expired! Resetting to 0 in DB");
            otpDAO.clearOtp(emailId);  // sets otp = "0", otpExpiry = null
            return false;
        }

        // Step 4: check if OTP matches
        if (!storedOtp.equals(enteredOtp.trim())) {
            System.out.println("OTP mismatch! Entered: " + enteredOtp
                    + " | Stored: " + storedOtp);
            return false;
        }

        System.out.println("OTP matched and valid!");
        return true;
        // Note: do NOT clear OTP here — clear only after password reset
    }
}