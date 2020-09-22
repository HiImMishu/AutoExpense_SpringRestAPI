package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.exception.CarNotFoundException;
import com.misiak.autoexpense.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;
    private UserService userService;

    @Override
    public Car getCar(long id) {
        Optional<Car> optionalCar = repository.findById(id);

        if (optionalCar.isPresent())
            return optionalCar.get();
        else
            throw new CarNotFoundException(id + "");
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = repository.findAll();

        if (cars.size() == 0)
            throw new CarNotFoundException("any");
        else
            return cars;
    }

    @Override
    public Car saveCar(Car car) {
        User user = getActualUser();
        car.setUser(user);
        car.setId(0);

        return repository.save(car);
    }

    private User getActualUser() {
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(token.getClaims().get("sub").toString());
    }

    @Override
    public void deleteCar(long id) {
        Car car = getCar(id);
        repository.delete(car);
    }

    @Override
    public Car updateCar(Car car) {
        Car carToUpdate = getCar(car.getId());
        car.setUser(carToUpdate.getUser());
        return repository.save(car);
    }
}
