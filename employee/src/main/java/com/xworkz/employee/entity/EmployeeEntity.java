package com.xworkz.employee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor

@NamedQuery(name = "findEmail",query="select e from EmployeeEntity e where e.emailId=:email")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String emailId;
    private Integer age;
    private String address;
    private Long mobileNumber;
    private String gender;
    private String password;

}
