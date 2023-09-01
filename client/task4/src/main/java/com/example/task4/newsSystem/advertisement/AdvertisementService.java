package com.example.task4.newsSystem.advertisement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public List<Advertisement> getAdvertisement() {
        return advertisementRepository.findAll();
    }
}
