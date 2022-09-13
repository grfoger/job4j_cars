package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.persistence.CarRepository;

import java.util.Collection;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Collection<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCarById(int carId) {
        return carRepository.getCarById(carId);
    }
}
