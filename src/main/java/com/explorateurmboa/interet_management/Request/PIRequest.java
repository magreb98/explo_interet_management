package com.explorateurmboa.interet_management.Request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PIRequest {
    private String nom;
    private String description;
    private Integer etoile;
    private Double longitude;
    private Double latitude;
    private MultipartFile[] images;
    private Long id_category;
    private String email;
    private String tel;
    private String whatsapp;
    private String url;


}
