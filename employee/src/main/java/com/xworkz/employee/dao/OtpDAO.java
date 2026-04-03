package com.xworkz.employee.dao;

public interface OtpDAO {
    boolean saveOtp(String emailId, String otp);
    String getOtp(String emailId);
    boolean clearOtp(String emailId);
}
