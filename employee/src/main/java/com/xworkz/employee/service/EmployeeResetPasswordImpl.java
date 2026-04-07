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
    private OtpDAO otpDAO;

    PasswordEncoder passwordEncoder = new PasswordEncryption();

    @Override
    public boolean resetPassword(String emailId, String password) {
        System.out.println("resetPassword called for: " + emailId);

        String encodedPassword = passwordEncoder.encode(password);
        boolean changed = loginPasswordDAO.changePassword(emailId, encodedPassword);

        if (changed) {
            otpDAO.clearOtp(emailId);  // sets otp = "0", otpExpiry = null in DB
            System.out.println("Password reset done. OTP set to 0 in DB");
            return true;
        }

        System.out.println("Password reset failed");
        return false;
    }
}