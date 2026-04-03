package com.xworkz.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;

    @NotNull
    @Size(min = 3,max = 10)
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name should contain only letters")
    private String userName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
    private String emailId;

    @NotNull
    @Min(value = 18,message = "Age must be greater than 18")
    @Max(value = 100,message = "Age must be less than 100")
    private Integer age;

    @NotNull
    @Size(min = 5, max = 100, message = "Address must be ")
    private String address;

    @NotNull
    @Min(value = 6000000000L, message = "Mobile number must start with 6-9")
    @Max(value = 9999999999L, message = "Invalid mobile number")
    private Long mobileNumber;

    @NotNull(message = "Please select a gender")
    private String gender;



    private MultipartFile file;

    private String imagePath;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "Password must contain uppercase, number, and special character")
    private String password;

    @NotNull
    private String confirmPassword;
//    private String loginAttempts;
}
