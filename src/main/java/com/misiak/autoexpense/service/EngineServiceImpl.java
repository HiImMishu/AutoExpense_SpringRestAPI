package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.entity.Engine;
import com.misiak.autoexpense.exception.EngineNotFoundException;
import com.misiak.autoexpense.repository.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EngineServiceImpl implements EngineService {

    private EngineRepository engineRepository;
    private CarService carService;

    @Override
    public Engine getEngine(long id) {
        Optional<Engine> optionalEngine = engineRepository.findById(id);

        if (optionalEngine.isPresent())
            return optionalEngine.get();
        else
            throw new EngineNotFoundException(id + "");
    }

    @Override
    public Engine saveEngine(Engine engine, long carId) {
        engine.setId(0);

        return updateOrSaveCarEngine(engine, carId).getEngine();
    }

    @Override
    public Engine updateEngine(Engine engine, long carId) {
        Engine engineToUpdate = getEngine(engine.getId());

        return updateOrSaveCarEngine(engine, carId).getEngine();
    }

    private Car updateOrSaveCarEngine(Engine engine, long carId) {
        Car car = carService.getCar(carId);
        car.setEngine(engine);
        return carService.updateCar(car);
    }
}
