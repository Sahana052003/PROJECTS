package com.xworkz.employee.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
public class ImageController {

    public ImageController() {
        System.out.println("Image Controller is Called");
    }

    @GetMapping("/serveImage")
    public void serveImage(@RequestParam("path") String imagePath,
                           HttpServletResponse response) throws IOException {
        System.out.println("Serving image from: " + imagePath);
        imagePath = imagePath.trim();

        File file = new File(imagePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found: " + imagePath);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".png")) {
            response.setContentType("image/png");
        } else if (fileName.endsWith(".gif")) {
            response.setContentType("image/gif");
        } else if (fileName.endsWith(".webp")) {
            response.setContentType("image/webp");
        } else {
            response.setContentType("image/jpeg");
        }

        response.setHeader("Cache-Control", "max-age=3600");

        try (InputStream inputStream =
                     new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream outputStream = response.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
            System.out.println("Image served successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("Error serving image: " + e.getMessage());
        }
    }
}