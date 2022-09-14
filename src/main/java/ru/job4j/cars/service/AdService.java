package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.persistence.AdRepository;

import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }


    public List<Ad> getAllAds() {
        return (List) adRepository.values();
    }

    public Ad add(Ad ad) {
       return adRepository.add(ad);
    }

    public Ad findById(int id) {
        return adRepository.getById(id);
    }

    public Ad update(Ad ad) {
        return adRepository.update(ad);
    }
}
