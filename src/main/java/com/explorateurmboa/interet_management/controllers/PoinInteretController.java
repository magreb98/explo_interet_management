package com.explorateurmboa.interet_management.controllers;

import com.explorateurmboa.interet_management.Request.PIRequest;
import com.explorateurmboa.interet_management.Request.ReservationRequest;
import com.explorateurmboa.interet_management.Request.ServiceRequest;
import com.explorateurmboa.interet_management.models.*;
import com.explorateurmboa.interet_management.repositories.CategorieRepository;
import com.explorateurmboa.interet_management.repositories.PointInteretRepository;
import com.explorateurmboa.interet_management.services.ImageService;
import com.explorateurmboa.interet_management.services.PointInteretService;
//import com.explorateurmboa.interet_management.services.ReservationService;
import com.explorateurmboa.interet_management.services.ServiceService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pi")
public class PoinInteretController {

    private static Logger logger =  LoggerFactory.getLogger(PoinInteretController.class);
    @Autowired
    PointInteretService pointInteretService;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    ServiceService serviceService;
    @Autowired
     private PointInteretRepository piRepository;
    @Autowired
    private ImageService imageService;
//    @Autowired
//    private ReservationService reservationService;

    @GetMapping
    public List<PointInteret> getAllPointInterets() {
        return (List<PointInteret>) piRepository.findAll();
    }

    @GetMapping("/{id_point}")
    public ResponseEntity<PointInteret> getPointInteret(@PathVariable Long id_point) {
        try {
            Optional<PointInteret> pointInteret = piRepository.findById(id_point);
            if (pointInteret.isPresent()) {
                return ResponseEntity.ok(pointInteret.get());
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PointInteret> savePointInteret(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam(value = "etoile", required = false) Integer etoile,
            @RequestParam("longitude") Double longitude,
            @RequestParam("latitude") Double latitude,
            @RequestParam("id_category") Long idCategory,
            @RequestParam("email") String email,
            @RequestParam("tel") String tel,
            @RequestParam(value = "whatsapp", required = false) String whatsapp,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "images", required = false) MultipartFile[] images,
            @RequestParam("id_quartier") Long id_quartier,
            @RequestParam("nom_coin") String nom_coin,
            @RequestParam("description_coin") String description_coin
    ) {
        logger.info("Catégorie ID : " + idCategory);

        // Vérification de l'existence de la catégorie
        Category category = categorieRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Création du contact
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setUrl(url);
        contact.setWhatsapp(whatsapp);
        contact.setTel(tel);

        // Création du point d'intérêt
        PointInteret pointInteret = pointInteretService.creerPointInteret(
                nom, description, etoile, longitude, latitude, category, contact, images
        );

        pointInteretService.ajouterQuartier(id_quartier, pointInteret.getId(), nom_coin,description_coin);

        return ResponseEntity.ok(pointInteret);
    }


//    @PutMapping("/{id_point}/{id_quartier}/ajouter-quartier")
//    public PointInteret ajouterQuartier(@PathVariable Long id_quartier, @PathVariable Long id_point, @RequestBody Coin coin) {
//
//        // Persist `Coin` explicitly to avoid transient property exception
//        return pointInteretService.ajouterQuartier(id_quartier, id_point, coin.getNom(), coin.getDescription());
//    }


    @PutMapping(value = "/{id_point}/ajouter-service", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PointInteret> updatePointInteret(@RequestParam("nom") String nom,
                                                           @RequestParam("description") String description,
                                                           @RequestParam("prix") double prix,
                                                           @RequestParam("image") MultipartFile image,
                                                           @PathVariable Long id_point) {
        PointInteret  pointInteret = serviceService.ajouterService(id_point, nom,description,prix,image);
        return ResponseEntity.ok(pointInteret);
    }

//    @PostMapping("/reservation}")
//    public Mono<ResponseEntity<Reservation>> reserverService(@RequestBody ReservationRequest reservationRequest) {
//        return  reservationService.addReservation(reservationRequest.getId_utilisateur(),reservationRequest.getId_service(),
//                reservationRequest.getNb_personne(),reservationRequest.getReservationPour())
//                .map(ResponseEntity::ok)
//                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
//    }


    @DeleteMapping("/{id_point}")
    public PointInteret deletePoint(@PathVariable Long id_point){
        return pointInteretService.deletePointInteret(id_point);
    }
}