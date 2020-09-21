package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarController {

    private CarService carService;

    @GetMapping("/{id}")
    public Car getCar(@PathVariable long id) {
        Optional<Car> car = carService.getCar(id);

        if(car.isPresent())
            return car.get();
        else
            throw new RuntimeException("Car not found with id - " + id);
    }
}
