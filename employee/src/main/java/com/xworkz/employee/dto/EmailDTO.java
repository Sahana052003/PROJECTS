// EmailDTO.java
package com.xworkz.employee.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EmailDTO {

    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+\\-]+@gmail\\.com$",
            message = "Please enter a valid @gmail.com email")
    private String emailId;
}