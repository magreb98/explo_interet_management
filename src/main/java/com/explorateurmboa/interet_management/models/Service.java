package com.explorateurmboa.interet_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Service {
    @Id
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "point_interet_id")
    @JsonIgnore
    private PointInteret pointInteret;

    @OneToMany(mappedBy = "service")
    private List<Reservation> reservationList = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }

}
