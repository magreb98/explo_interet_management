package com.explorateurmboa.interet_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "contacts")
public class Contact {
    @Id
    private Long id;
    private String email;
    private String tel;
    private String whatsapp;
    private String url;
    @JsonIgnore
    @OneToOne(mappedBy = "contact")
    private PointInteret pointInteret;
}
