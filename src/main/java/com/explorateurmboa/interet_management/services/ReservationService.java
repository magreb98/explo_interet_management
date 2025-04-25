//package com.explorateurmboa.interet_management.services;
//
//import com.explorateurmboa.interet_management.models.Reservation;
//import com.explorateurmboa.interet_management.repositories.ReservationRepository;
//import com.explorateurmboa.interet_management.repositories.ServiceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import reactor.core.scheduler.Schedulers;
//
//import java.time.LocalDateTime;
//
//@Service
//public class ReservationService {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//    @Autowired
//    private ServiceRepository serviceRepository;
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    @Value("${utilisateur.service.url}")
//    private String serviceUrl;
//
//    public Mono<Reservation> addReservation(Long id_utilisateur, Long id_service, int nombreReservation, LocalDateTime reservationPourDate) {
//        WebClient webClient = webClientBuilder.build();
//
//        Mono<Boolean> utilisateursExists = webClient.get()
//                .uri(serviceUrl + "api/users/{id}/exists", id_utilisateur)
//                .retrieve()
//                .bodyToMono(Boolean.class)
//                .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur exists in database")));
//
//        Mono<com.explorateurmboa.interet_management.models.Service> serviceMono = serviceRepository.findById(id_service)
//                .map(Mono::just)
//                .orElseGet(() -> Mono.error(new RuntimeException("service introuvable")));
//
//        return Mono.zip(utilisateursExists,serviceMono)
//                .publishOn(Schedulers.boundedElastic())
//                .flatMap(tuple -> {
//                    boolean utilisateurExists = tuple.getT1();
//                    com.explorateurmboa.interet_management.models.Service service = tuple.getT2();
//                    if(!utilisateurExists) {
//                        return  Mono.error(new RuntimeException("Utilisateur existe pas dans le database"));
//                    }
//                    Reservation reservation = new Reservation();
//                    reservation.setIdUtilisateur(id_utilisateur);
//                    reservation.setNombreReservation(nombreReservation);
//                    reservation.setReservationPourDate(reservationPourDate);
//                    service.getReservationList().add(reservation);
//                    serviceRepository.save(service);
//
//                    return Mono.just(reservationRepository.save(reservation));
//                });
//    }
//}
