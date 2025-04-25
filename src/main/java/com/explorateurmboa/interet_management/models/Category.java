package com.explorateurmboa.interet_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
    @Id
    private long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "")
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PointInteret> pointsInteret = new ArrayList<>();

    @PrePersist
    protected  void onCreate() { createdAt = LocalDate.now();
    }
    @PreUpdate
    protected  void onUpdate() {updatedAt = LocalDate.now();}

    public Category(){ }

    public Category(String name, LocalDate createdAt, LocalDate updatedAt, List<PointInteret> pointsInteret) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pointsInteret = pointsInteret;
    }
    
}
