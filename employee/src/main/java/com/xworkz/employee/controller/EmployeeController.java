package com.xworkz.employee.controller;

import com.xworkz.employee.dto.EmployeeDTO;
import com.xworkz.employee.dto.ImageDTO;
import com.xworkz.employee.dto.LoginDTO;
import com.xworkz.employee.encydecy.PasswordEncryption;
import com.xworkz.employee.service.EmployeeService;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
//
//    @Autowired
//    private ServletContext servletContext;


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
    public String signupPage(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        System.out.println("signUp page is Called");
        return "signup";
    }


    @PostMapping("/userlogin")
    public String registerUser(@ModelAttribute @Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) throws IOException {
        System.out.println("Employee entered a new data");
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeDTO", employeeDTO);

            if (bindingResult.hasFieldErrors("userName")) {
                model.addAttribute("userNameError",
                        bindingResult.getFieldError("userName").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("emailId")) {
                model.addAttribute("emailIdError",
                        bindingResult.getFieldError("emailId").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("age")) {
                model.addAttribute("ageError",
                        bindingResult.getFieldError("age").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("address")) {
                model.addAttribute("addressError",
                        bindingResult.getFieldError("address").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("mobileNumber")) {
                model.addAttribute("mobileNumberError",
                        bindingResult.getFieldError("mobileNumber").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("gender")) {
                model.addAttribute("genderError",
                        bindingResult.getFieldError("gender").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("passwordError",
                        bindingResult.getFieldError("password").getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("confirmPassword")) {
                model.addAttribute("confirmPasswordError",
                        bindingResult.getFieldError("confirmPassword").getDefaultMessage());
            }

            return "signup";
        }

        if (!employeeDTO.getPassword().equals(employeeDTO.getConfirmPassword())) {
            model.addAttribute("confirmPasswordError", "Passwords do not match");
            model.addAttribute("employeeDTO", employeeDTO);
            return "signup";
        }


        EmployeeDTO employeeDTO1 = employeeService.checkEmailExist(employeeDTO.getEmailId());
        System.out.println(employeeDTO1);
        if (employeeDTO1 != null) {
            model.addAttribute("error", "Email already exists! Please use another email");
            model.addAttribute("employeeDTO", employeeDTO);
            return "signup";
        } else {
            MultipartFile file = employeeDTO.getFile();

            if (file == null || file.isEmpty()) {
                model.addAttribute("imageError", "Please upload a file");
                model.addAttribute("employeeDTO", employeeDTO);
                return "signup";
            }
            // Instead of servletContext.getRealPath("/uploads")
//                String uploadDir = servletContext.getRealPath("/") + "uploads";
//                File directory=new File(uploadDir);
//                if (!directory.exists()) {
//                    boolean created = directory.mkdirs();
//                    System.out.println("Directory created: " + created + " at: " + uploadDir);
//                }

            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:\\img\\" + System.currentTimeMillis()
                    + "_" + file.getOriginalFilename());
//                Path path = Paths.get(uploadDir + File.separator +
//                        System.currentTimeMillis() + "_" + file.getOriginalFilename());
            System.out.println(path);
            Files.write(path, bytes);
//                Files.write(path,bytes);

            employeeDTO.setImagePath(path.toString());
            employeeService.checkEmployeeLoginData(employeeDTO);
            model.addAttribute("success", "registered successfully");

            return "login";
        }
    }


    @PostMapping("/userlogincheck")
    public String loginUser(@ModelAttribute @Valid LoginDTO loginDTO,
                            BindingResult bindingResult, Model model) {

        System.out.println("Login attempt: " + loginDTO.getEmailId());

        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("emailId")) {
                model.addAttribute("emailIdError",
                        bindingResult.getFieldError("emailId").getDefaultMessage());
            }
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("passwordError",
                        bindingResult.getFieldError("password").getDefaultMessage());
            }
            model.addAttribute("enteredEmail", loginDTO.getEmailId());
            return "login";
        }

        String result = employeeService.validateLogin(
                loginDTO.getEmailId(), loginDTO.getPassword());

        if (result.equals("success")) {
            EmployeeDTO loggedInUser = employeeService.checkEmailExist(loginDTO.getEmailId());
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("loginSuccess",
                    "Hello " + loggedInUser.getUserName() + ", Login Successfully");
            System.out.println("Login Successful");
            return "profilepage";

        } else if (result.equals("emailNotFound")) {
            System.out.println("Email not found");
            model.addAttribute("loginError", "Email not found! Please register first");
            model.addAttribute("enteredEmail", loginDTO.getEmailId());
            return "login";

        } else if (result.equals("Your account is Locked")) {
            model.addAttribute("loginError", "Your Account is Locked");
            model.addAttribute("enteredEmail", loginDTO.getEmailId());
            return "login";

        } else {
            System.out.println("Invalid Password");
            model.addAttribute("loginError", "Invalid Password or Email! Please try again");
            model.addAttribute("enteredEmail", loginDTO.getEmailId());
            return "login";
        }
    }
}