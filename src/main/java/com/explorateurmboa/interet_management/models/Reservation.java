package com.explorateurmboa.interet_management.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    private Long id;
    private Long idUtilisateur;
    private int nombreReservation;
    private LocalDateTime reservationPourDate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;


    @PrePersist
    protected  void onCreate(){
        this.createAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

    public Reservation(){}

    public Reservation(int nombreReservation, LocalDateTime reservationPourDate, LocalDateTime createAt,
                       LocalDateTime updateAt, Service service) {
        this.nombreReservation = nombreReservation;
        this.reservationPourDate = reservationPourDate;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.service = service;
    }
}
