package com.explorateurmboa.interet_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "point_interests")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PointInteret {
    @Id
    private Long id;
    private String nom;
    private String description;
    private Integer etoile;
    private Double longitude;
    private Double latitude;
    private boolean ispublishable = false;
    private boolean ispublished = false;
    private LocalDate createAt;
    private LocalDate updateAt;
    
    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(mappedBy = "pointInteret", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();
    
    @OneToMany(mappedBy = "pointInteret", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    @PrePersist
    protected void onCreate(){createAt = LocalDate.now();}
    @PreUpdate
    protected void onUpdate(){updateAt = LocalDate.now();}


    public void addService(Service service) {
        services.add(service);
        service.setPointInteret(this);
        updatePublishableStatus();

    }
    
    public void addImage(Image image) {
        images.add(image);
        image.setPointInteret(this);
    }
    
    public void updatePublishableStatus() {
        this.ispublishable = (coin != null) && (!services.isEmpty());
    }
}