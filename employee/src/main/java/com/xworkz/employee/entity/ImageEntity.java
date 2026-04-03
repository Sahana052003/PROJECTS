package com.xworkz.employee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee_image_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;
    private String contentType;
    private Long size;
    private String path;

//
//    @OneToOne(mappedBy = "imageEntity")
//    private EmployeeEntity employeeEntity;

}

