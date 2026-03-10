package com.xworkz.upgrade.service;

import com.xworkz.upgrade.dao.UpGradeDAO;
import com.xworkz.upgrade.dto.UpGradeDTO;
import com.xworkz.upgrade.entity.UpGradeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpGradeServiceImpl implements UpGradeService {

    @Autowired
    UpGradeDAO upGradeDAO;



    //Here in Service  defines a business logic (do Validations).
    //Where it will validates a user entered fields (Name,email,password).
    //If all the validations are correct ,(Convert DTO to Entity) and
    // then,it will pass to the DAO,Before passing the data to DAO ,
    // it will check the email whether the user entered email is already exists or not .
    @Override
    public String loginData(UpGradeDTO upGradeDTO) {
        String firstName = upGradeDTO.getFirstName();
        String lastName = upGradeDTO.getLastName();
        String email = upGradeDTO.getEmail();
        String password = upGradeDTO.getPassword();

        String pwd = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{6,}$";

        if (firstName != null && firstName.length() >= 4 &&
                lastName != null && lastName.length() >= 1 &&
                email != null && email.endsWith("@gmail.com") &&
                password != null && password.matches(pwd)) {

            UpGradeDTO upGradeDTO1 = checkEmailExist(email);
            if(upGradeDTO1==null) {
                UpGradeEntity entity = new UpGradeEntity();
                BeanUtils.copyProperties(upGradeDTO, entity);

                if (upGradeDAO.saveUpGradeData(entity)) {
                    return "Registered Successfully";
                }
                return "Failed To Register";
            }
            }
                return "Email already exists";
        }





    //Here, if a user entered a data and then
    // if he/she entered a existed email it checks if email exists by callinga DAO layer. ,
    // it will dispaly email exists alerady ,
    // otherwise if user entered a new email, it will registered.
    @Override
    public UpGradeDTO checkEmailExist(String email) {

        System.out.println("email is : " + email);
        UpGradeEntity emailExists = upGradeDAO.isEmailExist(email);
        if (emailExists != null) {
            UpGradeDTO upGradeDTO = new UpGradeDTO();
            BeanUtils.copyProperties(emailExists, upGradeDTO);
            return upGradeDTO;
        }
        return null;
    }







    //here,When a User want to login, If User enter a email and password
    // ,it will compares and then go to the controller
    //Otherwise, shows Incorrect email or user not found soon....
     @Override
    public boolean verifyLogin(String email, String password) {

        System.out.println("Logined through the email is : " + email);

        UpGradeEntity user = upGradeDAO.validateLogin(email);

        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        if (user.getPassword().equals(password)) {
            System.out.println("Password Is Matched with email");
            return true;
        } else {
            System.out.println("Incorrect password");
            return false;
        }
    }
}