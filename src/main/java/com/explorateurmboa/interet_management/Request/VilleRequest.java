package com.explorateurmboa.interet_management.Request;

import java.util.List;

public class VilleRequest {
    
    private Long id;
    private String name;
    private List<QuartierRequest> quartiers;

    public VilleRequest(Long id, String name, List<QuartierRequest> quartiers) {
        this.id = id;
        this.name = name;
        this.quartiers = quartiers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<QuartierRequest> getQuartiers() {
        return quartiers;
    }
}

