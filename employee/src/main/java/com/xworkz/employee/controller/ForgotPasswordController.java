package com.xworkz.employee.controller;

import com.xworkz.employee.dao.EmployeeDAO;
import com.xworkz.employee.dto.EmailDTO;
import com.xworkz.employee.dto.OtpDTO;
import com.xworkz.employee.dto.ResetPasswordDTO;
import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.service.OtpService;
import com.xworkz.employee.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Controller
@RequestMapping("/")
public class ForgotPasswordController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private OtpService otpService;

    @Autowired
    private ResetPasswordService resetPasswordService;


    @GetMapping("/forgetPassword")
    public String showForgotPage() {
        System.out.println("showForgotPage");
        return "email";
    }

//Verify Email
@PostMapping("/verifyOtp")
public String verifyOtp(@Valid @ModelAttribute OtpDTO otpDTO,
                        BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        if (bindingResult.hasFieldErrors("otp")) {
            model.addAttribute("otpError",
                    bindingResult.getFieldError("otp").getDefaultMessage());
        }
        model.addAttribute("emailId", otpDTO.getEmailId());
        return "otpVerification";
    }

    boolean verified = otpService.verifyOtp(
            otpDTO.getEmailId(), otpDTO.getOtp().trim());

    if (verified) {
        model.addAttribute("emailId", otpDTO.getEmailId());
        return "resetPassword";
    } else {
        // Check if expired or wrong
        model.addAttribute("otpError",
                "OTP is invalid or has expired! Please request a new OTP.");
        model.addAttribute("emailId", otpDTO.getEmailId());
        return "otpVerification";
    }
}

    // Also update verifyEmail to otp
    @GetMapping("/verifyEmail")
    public String verifyEmail(@Valid @ModelAttribute EmailDTO emailDTO,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("emailError",
                    bindingResult.getFieldError("emailId").getDefaultMessage());
            model.addAttribute("enteredEmail", emailDTO.getEmailId());
            return "email";
        }

        EmployeeEntity entity = employeeDAO.findByEmail(emailDTO.getEmailId());
        if (entity == null) {
            model.addAttribute("emailError", "Email not found! Please register first.");
            model.addAttribute("enteredEmail", emailDTO.getEmailId());
            return "email";
        }

        boolean sent = otpService.generateAndSendOtp(emailDTO.getEmailId());
        if (sent) {
            model.addAttribute("emailId", emailDTO.getEmailId());
            model.addAttribute("otpExpirySeconds", 30);
            model.addAttribute("successMessage",
                    "OTP sent to " + emailDTO.getEmailId() + ". Valid for 30 seconds.");
            return "otpVerification";
        } else {
            model.addAttribute("emailError", "Failed to send OTP. Please try again.");
            model.addAttribute("enteredEmail", emailDTO.getEmailId());
            return "email";
        }
    }
    @PostMapping("/resetPassword")
    public String resetPassword(
            @Valid ResetPasswordDTO dto,
            BindingResult bindingResult,
            Model model) {

        System.out.println(" resetPassword for: " + dto.getEmailId());


        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("passwordError",
                        bindingResult.getFieldError("password").getDefaultMessage());
            }
            if (bindingResult.hasFieldErrors("confirmPassword")) {
                model.addAttribute("confirmPasswordError",
                        bindingResult.getFieldError("confirmPassword").getDefaultMessage());
            }
            model.addAttribute("emailId", dto.getEmailId());
            return "resetPassword";
        }


        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            model.addAttribute("confirmPasswordError", "Passwords do not match!");
            model.addAttribute("emailId", dto.getEmailId());
            return "resetPassword";
        }

        boolean result = resetPasswordService.resetPassword(
                dto.getEmailId(), dto.getPassword());

        if (result) {
            model.addAttribute("success",
                    "Password reset successfully! Please login with your new password.");
            return "login";
        } else {
            model.addAttribute("resetError", "Reset failed. Please try again.");
            model.addAttribute("emailId", dto.getEmailId());
            return "resetPassword";
        }
    }
}
