package com.xworkz.employee.dao;

import java.time.LocalDateTime;

public interface OtpDAO {
    boolean saveOtp(String emailId, String otp);
    String getOtp(String emailId);
    boolean clearOtp(String emailId);
    public LocalDateTime getOtpExpiry(String emailId) ;
}
