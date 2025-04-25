package com.explorateurmboa.interet_management.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coins")
public class Coin {
    @Id
    private Long id;
    private String nom;
    private String description;
    
    @Setter
    @ManyToOne
    @JoinColumn(name = "quartier_id")
    private Quartier quartier;

    @JsonIgnore
    @OneToMany(mappedBy = "coin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointInteret> pointsInteret = new ArrayList<>();

    public void addPointInteret(PointInteret pointInteret) {
        pointsInteret.add(pointInteret);
        pointInteret.setCoin(this);
        pointInteret.updatePublishableStatus();
    }

}