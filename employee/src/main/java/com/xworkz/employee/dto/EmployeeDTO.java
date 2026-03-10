package com.xworkz.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String userName;
    private String emailId;
    private Integer age;
    private String address;
    private Long mobileNumber;
    private String gender;
    private String password;
    private String confirmPassword;
}
