package com.xworkz.employee.service;

import com.xworkz.employee.dao.LoginPasswordDAO;
import com.xworkz.employee.dao.OtpDAO;
import com.xworkz.employee.encydecy.PasswordEncoder;
import com.xworkz.employee.encydecy.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeResetPasswordImpl implements ResetPasswordService {

    @Autowired
    private LoginPasswordDAO loginPasswordDAO;

    @Autowired
    private OtpDAO otpDAO;   // ← inject OtpDAO here

    PasswordEncoder passwordEncoder = new PasswordEncryption();

    @Override
    public boolean resetPassword(String emailId, String password) {
        System.out.println("ResetPassword service called for: " + emailId);

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);

        boolean changed = loginPasswordDAO.changePassword(emailId, encodedPassword);

        if (changed) {
            otpDAO.clearOtp(emailId);
            System.out.println("OTP cleared from DB after successful password reset");
            return true;
        }

        System.out.println("Password change failed");
        return false;
    }
}