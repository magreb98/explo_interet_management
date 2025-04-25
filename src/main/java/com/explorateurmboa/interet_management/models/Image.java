package com.explorateurmboa.interet_management.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "images")
@Data
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Image {
    @Id
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "point_interet_id")
    private PointInteret pointInteret;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Image(){}

    public Image(String fileName, String filePath, String fileType, PointInteret pointInteret) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.pointInteret = pointInteret;
    }
    public Image(String fileName, String filePath, String fileType, Service service) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.service = service;
    }

}
