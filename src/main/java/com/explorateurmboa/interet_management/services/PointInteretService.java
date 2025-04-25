package com.explorateurmboa.interet_management.services;

import com.explorateurmboa.interet_management.models.*;
import com.explorateurmboa.interet_management.repositories.CoinRepository;
import com.explorateurmboa.interet_management.repositories.ImageRepository;
import com.explorateurmboa.interet_management.repositories.PointInteretRepository;
import com.explorateurmboa.interet_management.repositories.QuartierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PointInteretService {

    @Autowired
    private PointInteretRepository pointInteretRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private QuartierRepository quartierRepository;
    @Autowired
    private CoinRepository coinRepository;

    @Transactional
    public PointInteret creerPointInteret(String nom, String description, Integer etoile, Double longitude, Double latitude, Category category, Contact contact, MultipartFile[] images){

        PointInteret pointInteret = new PointInteret();
        pointInteret.setNom(nom);
        pointInteret.setDescription(description);
        pointInteret.setEtoile(etoile);
        pointInteret.setLongitude(longitude);
        pointInteret.setLatitude(latitude);
        pointInteret.setCategory(category);
        pointInteret = pointInteretRepository.save(pointInteret);

        if(contact != null) {
            pointInteret.setContact(contact);
        }
        pointInteret.updatePublishableStatus();

        if (images != null) {
            saveImages(images, pointInteret);
        }

        return pointInteretRepository.save(pointInteret);
    }

    public void ajouterQuartier(Long id_quartier, Long id_point, String nom_coin, String description_coin) {
        Quartier quartier = quartierRepository.findById(id_quartier)
                .orElseThrow(() -> new RuntimeException("Quartier Inexistant"));
        PointInteret pointInteret = pointInteretRepository.findById(id_point)
                .orElseThrow(() -> new RuntimeException("Inexistant"));
        Coin coin = new Coin();
        coin.setQuartier(quartier);
        coin.setNom(nom_coin);
        coin.setDescription(description_coin);

        // Save the transient Coin entity explicitly
        coin = coinRepository.save(coin);

        coin.addPointInteret(pointInteret);
        pointInteret.setCoin(coin);
        quartier.getCoins().add(coin);
        quartierRepository.save(quartier);

        pointInteretRepository.save(pointInteret);
        return;
    }

    public PointInteret deletePointInteret(Long id_point) {
        PointInteret pointInteret = pointInteretRepository.findById(id_point)
                .orElseThrow(() -> new RuntimeException("PointInteret Inexistant"));
        deleteImages(pointInteret);
        pointInteretRepository.delete(pointInteret);

        return pointInteret;
    }


    public void saveImages(MultipartFile[] images, PointInteret pointInteret) {

        for(MultipartFile file : images) {
            String filePath = imageService.saveImage(file);
            Image image = new Image(file.getOriginalFilename(), filePath, file.getContentType(), pointInteret);
            imageRepository.save(image);
            pointInteret.addImage(image);
        }
    }

    private void deleteImages(PointInteret pointInteret) {
        List<Image> images = pointInteret.getImages();
        for (Image image : images) {
            imageService.deleteImage(image.getFilePath());
            imageRepository.delete(image);
        }
        images.clear();
    }
}