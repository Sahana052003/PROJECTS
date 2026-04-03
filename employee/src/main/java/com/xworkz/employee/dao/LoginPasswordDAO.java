package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;

public interface LoginPasswordDAO {
    //Password changing
    boolean changePassword(String emailId,String password);

}
