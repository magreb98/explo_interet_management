package com.explorateurmboa.interet_management.Request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ServiceRequest {

    private String nom;
    private String description;
    private double prix;
    MultipartFile image;
}
