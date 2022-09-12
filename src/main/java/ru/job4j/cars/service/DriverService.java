package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.persistence.DriverRepository;

import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Optional<Driver> add(Driver user) {
        return driverRepository.addDriver(user);
    }

    public Optional<Driver> findUserByLoginAndPass(String login, String password) {
        return driverRepository.findUserByLoginAndPass(login, password);
    }
}
