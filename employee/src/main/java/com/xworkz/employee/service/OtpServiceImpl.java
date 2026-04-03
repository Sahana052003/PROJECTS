package com.xworkz.employee.service;

import com.xworkz.employee.dao.EmployeeDAO;
import com.xworkz.employee.dao.OtpDAO;
import com.xworkz.employee.dto.EmailDTO;
import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.utility.EmailUtility;
import com.xworkz.employee.utility.OtpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println("OtpService: verifyOtp  Email: " + emailId
                 + enteredOtp );
        if (enteredOtp == null || enteredOtp.trim().isEmpty()) {
            System.out.println("OTP is blank");
            return false;
        }

        String storedOtp = otpDAO.getOtp(emailId);
        System.out.println("Stored OTP in DB: " + storedOtp);

        if (storedOtp == null || storedOtp.isEmpty()) {
            System.out.println("No OTP found in DB for: " + emailId);
            return false;
        }

        if (!storedOtp.equals(enteredOtp.trim())) {
            System.out.println("OTP mismatch! Entered: "
                    + enteredOtp.trim() + " | Stored: " + storedOtp);
            return false;
        }

        System.out.println("OTP matched! Proceeding to reset page...");
        return true;
    }
}
