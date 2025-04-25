package com.explorateurmboa.interet_management.services;

import com.explorateurmboa.interet_management.models.Image;
import com.explorateurmboa.interet_management.models.PointInteret;
import com.explorateurmboa.interet_management.models.Service;
import com.explorateurmboa.interet_management.repositories.ImageRepository;
import com.explorateurmboa.interet_management.repositories.PointInteretRepository;
import com.explorateurmboa.interet_management.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    PointInteretRepository pointInteretRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;

    public PointInteret ajouterService(Long id_pointInteret, String nom, String description, double prix, MultipartFile imageFile) {
        PointInteret pointInteret = pointInteretRepository.findById(id_pointInteret)
                .orElseThrow(() -> new RuntimeException("Point d'intérêt non trouvé"));

        Service service = new Service();
        service.setNom(nom);
        service.setDescription(description);
        service.setPrix(prix);
        service.setPointInteret(pointInteret);

        serviceRepository.save(service);

        if (imageFile != null) {
            saveImage(imageFile, service);
        }
        pointInteret.addService(service);
        serviceRepository.save(service);

        return pointInteretRepository.save(pointInteret);
    }

    public void saveImage(MultipartFile image, Service service) {


            String filePath = imageService.saveImage(image);
            Image img = new Image(image.getOriginalFilename(), filePath, image.getContentType(), service);
            imageRepository.save(img);

    }
}
