package com.explorateurmboa.interet_management.Request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private Long id_utilisateur;
    private Long id_service;
    private int nb_personne;
    private LocalDateTime reservationPour;
}
