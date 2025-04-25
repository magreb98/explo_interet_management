package com.explorateurmboa.interet_management.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.explorateurmboa.interet_management.Request.QuartierRequest;
import com.explorateurmboa.interet_management.Request.VilleRequest;
import com.explorateurmboa.interet_management.models.Ville;
import com.explorateurmboa.interet_management.models.Quartier;
import com.explorateurmboa.interet_management.repositories.QuartierRepository;
import com.explorateurmboa.interet_management.repositories.VilleRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/villes")
public class VilleController {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private QuartierRepository quartierRepository;

    @GetMapping
    public ResponseEntity<List<Ville>> getAll() {
        List<Ville> villes = villeRepository.findAll();
        if (villes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getById(@PathVariable Long id) {
        Optional<Ville> ville = villeRepository.findById(id);
        return ville.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ville> create(@RequestBody Ville ville) {
        try {
            Ville savedVille = villeRepository.save(ville);
            return new ResponseEntity<>(savedVille, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ville> update(@PathVariable Long id, @RequestBody Ville villeDetails) {
        Optional<Ville> existingVilleOptional = villeRepository.findById(id);

        if (existingVilleOptional.isPresent()) {
            Ville existingVille = existingVilleOptional.get();
            existingVille.setName(villeDetails.getName());
            existingVille.setUpdatedAt(villeDetails.getUpdatedAt());

            return new ResponseEntity<>(villeRepository.save(existingVille), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/ajouter-quartier")
public ResponseEntity<Ville> ajouterQuartier(@PathVariable Long id, @RequestBody Quartier quartier) {
    return villeRepository.findById(id).map(ville -> {
        quartier.setVille(ville);
        Quartier savedQuartier = quartierRepository.save(quartier);
        ville.getQuartiers().add(savedQuartier);

        return new ResponseEntity<>(villeRepository.save(ville), HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            villeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    // Endpoint pour récupérer toutes les villes avec leurs quartiers
    @GetMapping("/avec-quartiers")
public ResponseEntity<List<VilleRequest>> getAllVillesWithQuartiers() {
    List<Ville> villes = villeRepository.findAll();

    if (villes.isEmpty()) return ResponseEntity.noContent().build();

    List<VilleRequest> villeRequests = villes.stream().map(ville -> new VilleRequest(
            ville.getId(),
            ville.getName(),
            ville.getQuartiers().stream()
                    .map(q -> new QuartierRequest(q.getId(), q.getNom()))
                    .collect(Collectors.toList())
    )).collect(Collectors.toList());

    return ResponseEntity.ok(villeRequests);
}


    // Endpoint pour récupérer une ville spécifique avec ses quartiers
    @GetMapping("/{id}/avec-quartiers")
public ResponseEntity<VilleRequest> getVilleWithQuartiers(@PathVariable Long id) {
    return villeRepository.findById(id)
            .map(ville -> new ResponseEntity<>(new VilleRequest(
                    ville.getId(),
                    ville.getName(),
                    ville.getQuartiers().stream()
                            .map(q -> new QuartierRequest(q.getId(), q.getNom()))
                            .collect(Collectors.toList())
            ), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

}
