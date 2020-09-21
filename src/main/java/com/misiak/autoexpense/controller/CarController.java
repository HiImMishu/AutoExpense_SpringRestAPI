package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.exception.CarNotFoundException;
import com.misiak.autoexpense.exception.UserNotFoundException;
import com.misiak.autoexpense.service.CarService;
import com.misiak.autoexpense.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarController {

    private CarService carService;
    private UserService userService;

    @GetMapping("/{id}")
    public Car getCar(@PathVariable long id) {
        Optional<Car> car = carService.getCar(id);

        if(car.isPresent())
            return car.get();
        else
            throw new CarNotFoundException(id + "");
    }

    @GetMapping
    public List<Car> getCars() {
        List<Car> cars = carService.getCars();

        if(cars.size() == 0)
            throw  new CarNotFoundException("all");
        else
            return cars;
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car, Principal principal) {
        User user = getActualUser(principal);
        car.setUser(user);
        car.setId(0);

        return carService.saveCar(car);
    }

    private User getActualUser(Principal principal) {
        Optional<User> optionalUser = userService.getUser(principal.getName());
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new UserNotFoundException(principal.getName());
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car, Principal principal) {
        if (actualUserCarExists(car.getId())) {
            User user = getActualUser(principal);
            car.setUser(user);
            return carService.saveCar(car);
        }
        else
            throw new CarNotFoundException(car.getId() + "");
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id) {
        if(actualUserCarExists(id)) {
            Car car = carService.getCar(id).get();
            carService.deleteCar(car);
        }
        else
            throw new CarNotFoundException(id + "");
    }

    private boolean actualUserCarExists(long id) {
        Optional<Car> optionalCar = carService.getCar(id);
        return optionalCar.isPresent();
    }
}
