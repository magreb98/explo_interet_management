package com.explorateurmboa.interet_management.models;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "transports")
public class Transport {
    @Id
    private Long id;
    private double tarifJour;
    private double tarifNuit;
    private String secteur;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "quartier1_id")
    private Quartier quartier1;

    @ManyToOne
    @JoinColumn(name = "quartier2_id")
    private Quartier quartier2;

    public Transport(){}

    public Transport(double tarifJour, double tarifNuit, String secteur, Timestamp createdAt, Timestamp updatedAt,
            Quartier quartier1, Quartier quartier2) {
        this.tarifJour = tarifJour;
        this.tarifNuit = tarifNuit;
        this.secteur = secteur;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quartier1 = quartier1;
        this.quartier2 = quartier2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTarifJour() {
        return tarifJour;
    }

    public void setTarifJour(double tarifJour) {
        this.tarifJour = tarifJour;
    }

    public double getTarifNuit() {
        return tarifNuit;
    }

    public void setTarifNuit(double tarifNuit) {
        this.tarifNuit = tarifNuit;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Quartier getQuartier1() {
        return quartier1;
    }

    public void setQuartier1(Quartier quartier1) {
        this.quartier1 = quartier1;
    }

    public Quartier getQuartier2() {
        return quartier2;
    }

    public void setQuartier2(Quartier quartier2) {
        this.quartier2 = quartier2;
    }

    
}
