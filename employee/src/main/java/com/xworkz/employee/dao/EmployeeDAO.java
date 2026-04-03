package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;

public interface EmployeeDAO {
  void saveEmployeeLoginData(EmployeeEntity employeeEntity);

  EmployeeEntity findByEmail(String emailId);






  //Update
EmployeeEntity getDetailsBasedOnId(int id);

  boolean updateEmployeeDetails(EmployeeEntity employeeEntity);

}
