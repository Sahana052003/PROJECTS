package com.xworkz.employee.service;

import com.xworkz.employee.dao.EmployeeDAO;
import com.xworkz.employee.dao.ImageDAO;
import com.xworkz.employee.dto.EmployeeDTO;
import com.xworkz.employee.encydecy.PasswordEncoder;
import com.xworkz.employee.encydecy.PasswordEncryption;
import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.entity.ImageEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    ImageDAO imageDAO;


    PasswordEncoder passwordEncoder = new PasswordEncryption();

    @Override
    public boolean checkEmployeeLoginData(EmployeeDTO employeeDTO) {
        String emailId = employeeDTO.getEmailId();
        System.out.println("Service method is Called");

        EmployeeDTO employeeDTO1 = checkEmailExist(emailId);

        MultipartFile file = employeeDTO.getFile();

        if (file == null || file.isEmpty()) {
            return false;
        }

        String contentType = file.getContentType();
        if (!(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            return true;
        }

        if (employeeDTO1 == null) {

            EmployeeEntity employeeEntity = new EmployeeEntity();
            BeanUtils.copyProperties(employeeDTO, employeeEntity);

            String encryptedPassword = passwordEncoder.encode(employeeDTO.getPassword());
            employeeEntity.setPassword(encryptedPassword);
            System.out.println("Encrypted Password : " + encryptedPassword);


            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setFileName(employeeDTO.getFile().getOriginalFilename());
            imageEntity.setContentType(employeeDTO.getFile().getContentType());
            imageEntity.setSize(employeeDTO.getFile().getSize());
            imageEntity.setPath(employeeDTO.getImagePath());

            employeeEntity.setImageEntity(imageEntity);

            employeeDAO.saveEmployeeLoginData(employeeEntity);
            System.out.println("Data Saved Successfully");


            return true;

        } else {
            System.out.println("Email already existed : " + emailId);
            boolean updateEmployee = updateEmployee(employeeDTO);
            if (updateEmployee) {
                System.out.println("Data is Updated Successfully");
                return true;
            } else {
                System.out.println("Data Is not Updated");
                return false;
            }
        }
    }


    @Override
    public EmployeeDTO checkEmailExist(String emailId) {
        System.out.println("Checking email : " + emailId);

        EmployeeEntity emailExists = employeeDAO.findByEmail(emailId);
        if (emailExists != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(emailExists, employeeDTO);
            if (emailExists.getImageEntity() != null) {
                employeeDTO.setImagePath(emailExists.getImageEntity().getPath());
            }
            return employeeDTO;
        }
        return null;
    }


    @Override
    public String validateLogin(String emailId, String rawPassword) {
        System.out.println("validateLogin Service is Called");

        EmployeeEntity employeeEntity = employeeDAO.findByEmail(emailId);

        int noOfAttempts;
        int maxAttempts = 3;

        if (employeeEntity == null) {
            System.out.println("Email not found in DB");
            return "emailNotFound";
        } else {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employeeEntity, employeeDTO);

            boolean isPasswordMatch = passwordEncoder.match(rawPassword, employeeDTO.getPassword());
            System.out.println("Password match result : " + isPasswordMatch);
            if (isPasswordMatch) {
                System.out.println("Login Successfully : " + emailId);
                employeeEntity.setLoginAttempts(0);
                employeeDAO.updateEmployeeDetails(employeeEntity);
                return "success";
            } else {

                if (employeeEntity.getLoginAttempts() >= maxAttempts) {
                    System.out.println("Account already Locked : " + emailId);
                    return "Your account is Locked";
                }

                noOfAttempts = employeeEntity.getLoginAttempts() + 1;
                employeeEntity.setLoginAttempts(noOfAttempts);
                employeeDAO.updateEmployeeDetails(employeeEntity);
                System.out.println("Invalid Password for emailId : " + emailId);
                System.out.println("Login attempts : " + noOfAttempts);
                if (noOfAttempts >= maxAttempts) {
                    System.out.println("Account is Locked as email is : " + emailId);
                    return "Your account is Locked";
                }
                return "Invalid Password";
            }
        }
    }

    @Override
    public EmployeeDTO getEmployeeData(int id) {
        EmployeeEntity employeeEntity = employeeDAO.getDetailsBasedOnId(id);
        if (id != 0) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employeeEntity, employeeDTO);
            if (employeeEntity.getImageEntity() != null) {
                employeeDTO.setImagePath(employeeEntity.getImageEntity().getPath());
            }
            return employeeDTO;
        }
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        System.out.println("UpdateEmployee Service Called");

        EmployeeEntity existingEntity = employeeDAO.findByEmail(employeeDTO.getEmailId());
        if (existingEntity == null) return false;

        // Update fields
        if (employeeDTO.getUserName() != null)
            existingEntity.setUserName(employeeDTO.getUserName());
        if (employeeDTO.getAge() != null)
            existingEntity.setAge(employeeDTO.getAge());
        if (employeeDTO.getAddress() != null)
            existingEntity.setAddress(employeeDTO.getAddress());

        // Password
        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().trim().isEmpty()) {
            existingEntity.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        }

        // Image update
        if (employeeDTO.getImagePath() != null && !employeeDTO.getImagePath().trim().isEmpty()) {
            if (existingEntity.getImageEntity() != null) {
                existingEntity.getImageEntity().setPath(employeeDTO.getImagePath());
                if (employeeDTO.getFile() != null && !employeeDTO.getFile().isEmpty()) {
                    existingEntity.getImageEntity().setFileName(
                            employeeDTO.getFile().getOriginalFilename());
                    existingEntity.getImageEntity().setContentType(
                            employeeDTO.getFile().getContentType());
                    existingEntity.getImageEntity().setSize(
                            employeeDTO.getFile().getSize());
                }
            }
        }

        return employeeDAO.updateEmployeeDetails(existingEntity);
    }
}