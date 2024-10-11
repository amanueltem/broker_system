package com.coderscampus.BrokerSystem.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderscampus.BrokerSystem.domain.Place;
import com.coderscampus.BrokerSystem.dto.PlaceDto;
import com.coderscampus.BrokerSystem.repository.PlaceRepository;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepo;

    public Place savePlace(PlaceDto placeDto) {
        Place newPlace = new Place();
        newPlace.setArea(placeDto.getArea());
        newPlace.setPrice(placeDto.getPrice());
        newPlace.setAddress(placeDto.getAddress());
        newPlace.setStatus("Pending");
        newPlace.setImageUrl(placeDto.getImageUrl());
        return placeRepo.save(newPlace);
    }

    public Place updatePlace(Place place) {
        return placeRepo.save(place);
    }

    @Transactional
    public void deletPlace(Long placeId) {
        // Find the Place object by ID
        Place place = placeRepo.findById(placeId)
            .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        // Construct the full path to the image file
        String imagePath = place.getImageUrl(); // Assuming this returns the relative path
        String fullPath = Paths.get("src/main/resources/static", imagePath).toString(); // Adjust the base path as needed

        // Delete the image file from the filesystem
        File imageFile = new File(fullPath);
        if (imageFile.exists()) {
            if (imageFile.delete()) {
                System.out.println("Image deleted successfully: " + fullPath);
            } else {
                System.out.println("Failed to delete image: " + fullPath);
            }
        } else {
            System.out.println("Image file does not exist: " + fullPath);
        }

        // Now delete the Place from the database
        placeRepo.deleteById(placeId);
    }

    public List<Place> getAllPlaceses() {
        return placeRepo.findAll();
    }
}
