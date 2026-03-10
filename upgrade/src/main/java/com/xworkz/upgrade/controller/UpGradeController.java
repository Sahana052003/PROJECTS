package com.xworkz.upgrade.controller;

import com.xworkz.upgrade.dto.UpGradeDTO;
import com.xworkz.upgrade.service.UpGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UpGradeController {
    public UpGradeController() {
        System.out.println("UpGradeController is Called");
    }

    @Autowired
    UpGradeService upGradeService;


    //This is for home page index.jsp to display in the browser
    @PostMapping("/showHomePage")
    public String showHomePage() {
        System.out.println("Home Page is Displayed");
        return "index";
    }


    //This is for index.jsp file here when a user click a LogIn button,
    // the mapping(/login)Get request returns the login.jsp page.
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("Login Page is Displayed");
        return "login";
    }


//    @PostMapping("/loginData")
//    public String showLoginPage() {
//        return "login";
//    }


    //This is for index.jsp file here when a user click a register
    // button it will send to the register.jsp page
    @PostMapping("/register")
    public String showRegisterPage() {
        System.out.println("Register Page is Displayed");
        return "register";
    }

    //This is for register.jsp file here user entered a data
    //It receives  a user data through UpGrade DTO and pass that to the service layer
    //if the data entered is correct ,it will redirected to index.jsp file
    //If data is incorrect ,it will shows an error message in the same register.jsp file
    @PostMapping("/registerData")
    public String saveRegisterPage(UpGradeDTO upGradeDTO, Model model) {

        String loginData = upGradeService.loginData(upGradeDTO);
        if (loginData.equalsIgnoreCase("Registered Successfully")) {
            model.addAttribute("message", loginData);
            return "index";
        } else if (loginData.equalsIgnoreCase("Failed To Register")) {
            model.addAttribute("errormsg", loginData);
            return "register";
        } else {
            model.addAttribute("errormsg", loginData);
            return "register";
        }
    }



    //Here ,it will check if a user entered a different email or not
    //if a user entered a existed email ,it will display the email is
    // alredy regidtered.
    //otherwise,if he entered a different email along with data ,it will got to service layer.
    @PostMapping("/checkEmail")
    @ResponseBody  // Important: returns plain response, not a JSP
    public String checkEmail(@RequestParam("email") String email) {

        UpGradeDTO  isEmailValid = upGradeService.checkEmailExist(email);

        if (isEmailValid!=null) {
            return "exists";  // Email already exists
        } else {
            return "not exists";  // return new email
        }
    }







    //Here,when a user click a login.jsp button ,it will go to login page
    //Here, if user entered a email and password from which stored in DB,
    // it will redirected to index.jsp

    //Otherwise , if User entered a wrong email or wrong password,
    //it will dispaly Invalid email or password in this same page.
    @PostMapping("/loginData")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        boolean result = upGradeService.verifyLogin(email, password);

        if (result) {
            System.out.println(" Login successfully");
            model.addAttribute("userEmail", email);
            return "index";
        } else {
            System.out.println("Failed to Login");
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }
}
