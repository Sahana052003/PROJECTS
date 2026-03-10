package com.xworkz.employee.service;

import com.xworkz.employee.dto.EmployeeDTO;

public interface EmployeeService {
  boolean checkEmployeeLoginData(EmployeeDTO employeeDTO);
  EmployeeDTO checkEmailExist(String emailId);

}
