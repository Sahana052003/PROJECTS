package com.xworkz.employee.controller;

import com.xworkz.employee.dto.EmployeeDTO;
import com.xworkz.employee.service.EmployeeService;
import com.xworkz.employee.util.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    public EmployeeController() {
        System.out.println("EmployeeController is Called");
    }
    @GetMapping
    public String homePage() {
        System.out.println("Home page called");
        return "index";
    }
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("Login Page is Called");
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        System.out.println("signUp page is Called");
        return "signup";
    }


    @PostMapping("/userlogin")
    public String registerUser(EmployeeDTO employeeDTO, Model model){
        System.out.println("Employee entered a new data");
        EmployeeDTO employeeDTO1 = employeeService.checkEmailExist(employeeDTO.getEmailId());
        if(employeeDTO1 != null) {
            model.addAttribute("error", "Email already exists! Please use another email");
            model.addAttribute("employeeDTO", employeeDTO);
            return "signup";
        }else{
            employeeService.checkEmployeeLoginData(employeeDTO);
            model.addAttribute("success","registered successfully");
            return "index";
        }
    }


    @PostMapping("/userlogincheck")
    public String loginUser(EmployeeDTO employeeDTO, Model model) {
        System.out.println("Login attempt: " + employeeDTO.getEmailId());
        EmployeeDTO existingUser = employeeService.checkEmailExist(employeeDTO.getEmailId());

        if (existingUser != null) {
            String encryptedInputPassword = PasswordEncryption.encrypt(employeeDTO.getPassword());
            if (encryptedInputPassword.equals(existingUser.getPassword())) {
                return "index";
            }
        }
        model.addAttribute("loginError", "Invalid Email or Password");
        return "login";
    }
}
