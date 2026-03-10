package com.xworkz.employee.service;


import com.xworkz.employee.dao.EmployeeDAO;
import com.xworkz.employee.dto.EmployeeDTO;
import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.util.PasswordEncryption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;


    @Override
    public boolean checkEmployeeLoginData(EmployeeDTO employeeDTO) {
        String emailId = employeeDTO.getEmailId();

        System.out.println("Service method is Called");
        EmployeeEntity employeeEntity =new EmployeeEntity();
        EmployeeDTO employeeDTO1 = checkEmailExist(emailId);
        if(employeeDTO1!=null) {
            System.out.println("email already existed is : " + emailId);
        return false;
        }else{
        BeanUtils.copyProperties(employeeDTO, employeeEntity);
            String encryptedPassword = PasswordEncryption.encrypt(employeeDTO.getPassword());
            employeeEntity.setPassword(encryptedPassword);
            employeeDAO.saveEmployeeLoginData(employeeEntity);
            System.out.println("New Data Is registered ");
        return true;
        }
    }



    @Override
    public EmployeeDTO checkEmailExist(String emailId) {

        System.out.println("email is : " + emailId);
        EmployeeEntity emailExists = employeeDAO.findByEmail(emailId);
        if (emailExists != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(emailExists, employeeDTO);
            return employeeDTO;
        }
        return null;
    }
}