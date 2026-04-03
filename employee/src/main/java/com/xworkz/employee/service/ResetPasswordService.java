package com.xworkz.employee.service;

import com.xworkz.employee.dto.EmployeeDTO;

public interface ResetPasswordService {
    //reset Password
    boolean resetPassword(String emailId,String password);
}
