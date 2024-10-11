package com.coderscampus.BrokerSystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RequestMapping("/api/uploadImage")
@RestController
public class ImageUploadController {

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/images/";

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file selected for upload";
        }
        try {
            // Create a unique filename
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(UPLOAD_DIRECTORY + filename);
            
            // Ensure the directory exists
            Files.createDirectories(filepath.getParent());
            
            // Save the file to the specified path
            Files.write(filepath, file.getBytes());
            
            // Return the URL path to be stored in the database
            return "/images/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed";
        }
    }
}
