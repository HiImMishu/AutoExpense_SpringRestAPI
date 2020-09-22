package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarController {

    private CarService carService;

    @GetMapping("/{id}")
    public Car getCar(@PathVariable long id) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }
}
