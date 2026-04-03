// OtpDTO.java
package com.xworkz.employee.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OtpDTO {

    @NotBlank(message = "Email cannot be empty")
    private String emailId;

    @NotBlank(message = "OTP cannot be empty")
    @Pattern(regexp = "[0-9]{6}", message = "OTP must be exactly 6 digits")
    private String otp;
}