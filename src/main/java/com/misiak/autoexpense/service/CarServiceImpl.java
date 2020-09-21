package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Override
    public Optional<Car> getCar(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> getCars() {
        return repository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return repository.save(car);
    }

    @Override
    public void deleteCar(Car car) {
        repository.delete(car);
    }
}
