package com.xworkz.employee.service;

import com.xworkz.employee.dto.EmployeeDTO;

import java.io.IOException;

public interface EmployeeService {
  //CREATE AND SAVE
  boolean checkEmployeeLoginData(EmployeeDTO employeeDTO);
  EmployeeDTO checkEmailExist(String emailId);
  String validateLogin(String emailId,String password);



  //UPDATE
  EmployeeDTO getEmployeeData(int  id);
  boolean updateEmployee(EmployeeDTO employeeDTO);


}