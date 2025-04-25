package com.explorateurmboa.interet_management.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Data
@Table(name = "villes")
public class Ville {
    @Id
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @JsonIgnore
    @OneToMany(mappedBy = "ville")
    private List<Quartier> quartiers = new ArrayList<>();

    @PrePersist
    protected void onCreate(){ createdAt = LocalDate.now(); }

    @PreUpdate
    protected void onUpdate(){ updatedAt = LocalDate.now(); }

    public Ville(){}

    public Ville(String name, LocalDate createdAt, LocalDate updatedAt, List<Quartier> quartiers) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quartiers = quartiers;
    }

    public void addQuartier(Quartier quartier){
        List<Quartier> quartiers = new ArrayList<>();
        quartiers.add(quartier);
        this.quartiers = quartiers;
    }

    
}
