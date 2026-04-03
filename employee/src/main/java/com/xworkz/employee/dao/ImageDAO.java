package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDAO {
    void saveImageFile(EmployeeEntity employeeEntity);
}