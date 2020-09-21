package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    public Optional<Car> getCar(long id);
    public List<Car> getCars();
    public Car saveCar(Car car);
    public void deleteCar(Car car);
}
