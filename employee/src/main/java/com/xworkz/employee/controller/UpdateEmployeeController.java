package com.xworkz.employee.controller;

import com.xworkz.employee.dto.EmployeeDTO;
import com.xworkz.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
public class UpdateEmployeeController {
    public UpdateEmployeeController(){
        System.out.println("UpdateEmployeeController is called");

    }
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/editProfile")
    public String editProfilePage(@RequestParam("id") int id, Model model) {
        System.out.println("Edit Employee Details ");
       EmployeeDTO employeeData = employeeService.getEmployeeData(id);
        if(employeeData!=null) {
          model.addAttribute("employeeDTO", employeeData);
            model.addAttribute("loggedInUser", employeeData);
            return "editProfile";
        }
         return "editProfile";
    }

    @GetMapping("/profilepage")
    public String viewProfilePage(@RequestParam("id") int id, Model model) {
        System.out.println("View Profile Page Called");
        EmployeeDTO employeeDTO = employeeService.getEmployeeData(id);
        if (employeeDTO != null) {
            model.addAttribute("loggedInUser", employeeDTO);
        }
        return "viewprofile";
    }


    @GetMapping("/viewprofile")
    public String viewProfile(Model model, @RequestParam("id") int id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeData(id);
        model.addAttribute("loggedInUser", employeeDTO);
        return "profilepage";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@Valid EmployeeDTO employeeDTO,BindingResult bindingResult, Model model) throws IOException {
        System.out.println("Update Employee Controller Called: " + employeeDTO.getEmailId());

        EmployeeDTO existing = employeeService.getEmployeeData(employeeDTO.getId());


        // Handle new image upload if provided
        MultipartFile file = employeeDTO.getFile();
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:\\img\\" + System.currentTimeMillis()
                    + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            employeeDTO.setImagePath(path.toString());
        } else {
            // Keep existing image path — fetch it from DB
            if (existing != null) {
                employeeDTO.setImagePath(existing.getImagePath());
            }
        }

        boolean result = employeeService.updateEmployee(employeeDTO);

        if (result) {
            EmployeeDTO employeeDTO1 = employeeService.getEmployeeData(employeeDTO.getId());
            model.addAttribute("loggedInUser", employeeDTO1);
            model.addAttribute("updateSuccess", "Profile updated successfully!");
            return "profilepage";
        } else {
            model.addAttribute("updateError", "Update failed! Please try again.");
            model.addAttribute("employeeDTO", employeeDTO);
            model.addAttribute("loggedInUser", employeeDTO);
            return "editProfile";
        }
    }

    @GetMapping("/myaccount")
    public String myAccountPage(@RequestParam("id") int id, Model model) {
        System.out.println("My Account Page Called for id: " + id);
        EmployeeDTO employeeDTO = employeeService.getEmployeeData(id);
        if (employeeDTO != null) {
            model.addAttribute("loggedInUser", employeeDTO);
            return "myaccount";
        }
        return "myaccount";
    }

}