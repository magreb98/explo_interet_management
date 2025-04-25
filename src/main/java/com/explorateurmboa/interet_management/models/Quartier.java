package com.explorateurmboa.interet_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quartiers")
@Data
@Setter
@Getter
public class Quartier {
    @Id
    private Long id;

    private String nom;
    private LocalDate createdAt;
    private LocalDate updatedAt;


    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @JsonIgnore
    @OneToMany(mappedBy = "quartier")
    private List<Coin> coins = new ArrayList<>();

    @PrePersist
    protected  void oncreate(){ createdAt = LocalDate.now(); }

    @PreUpdate
    protected void onupdate(){ updatedAt = LocalDate.now(); }

    public Quartier(){}

    public Quartier(String nom, LocalDate createdAt, LocalDate updatedAt,
                    List<Coin> coins, Ville ville) {
        this.nom = nom;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ville = ville;
        this.coins = coins;
    }

}
