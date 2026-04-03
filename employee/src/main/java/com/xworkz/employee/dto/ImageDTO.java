package com.xworkz.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Integer id;
    private String fileName;
    private String contentType;
    private Long size;
    private String path;
    private MultipartFile file;
}