package com.xworkz.employee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private int loginAttempts;

    private String otp;

    @OneToOne(cascade = CascadeType.ALL)//What the things had done in Employee, it will act the same as to Image automatically
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

}





//@OneToOne----→One record in One table is related to one record in another table.(means one applicant  contains one  image)
//CascadeType.ALL ---->can save,update,delete an image
//    @JoinColumn(name = "image_id")-->Creates a foreign key column asnmaed "image_id" in employee_tb table