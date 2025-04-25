package com.explorateurmboa.interet_management.Request;

public class QuartierRequest {
    private Long id;
    private String nom;

    public QuartierRequest(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
