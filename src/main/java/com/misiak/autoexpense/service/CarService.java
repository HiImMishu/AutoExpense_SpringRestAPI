package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;

import java.util.List;

public interface CarService {

    public Car getCar(long id);
    public List<Car> getCars();
    public Car saveCar(Car car);
    public void deleteCar(long id);
    public Car updateCar(Car car);
}
