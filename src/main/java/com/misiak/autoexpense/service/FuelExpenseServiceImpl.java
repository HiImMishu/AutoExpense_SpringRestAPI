package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.entity.FuelExpense;
import com.misiak.autoexpense.exception.FuelExpenseNotFoundException;
import com.misiak.autoexpense.repository.FuelExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelExpenseServiceImpl implements FuelExpenseService {

    private FuelExpenseRepository repository;
    private CarService carService;

    @Override
    public FuelExpense getFuelExpense(long id) {
        Optional<FuelExpense> optionalFuelExpense = repository.findById(id);

        if (optionalFuelExpense.isPresent())
            return optionalFuelExpense.get();
        else
            throw new FuelExpenseNotFoundException(id + "");
    }

    @Override
    public List<FuelExpense> getFuelExpenses(long carId) {
        Car car = carService.getCar(carId);
        List<FuelExpense> fuelExpenses = repository.findAllByCarId(carId);

        if (fuelExpenses.size() == 0)
            throw new FuelExpenseNotFoundException("any for car with id " + carId);
        else
            return fuelExpenses;
    }

    @Override
    public FuelExpense addFuelExpense(FuelExpense fuelExpense, long carId) {
        Car car = carService.getCar(carId);
        fuelExpense.setCar(car);

        return repository.save(fuelExpense);
    }

    @Override
    public FuelExpense updateFuelExpense(FuelExpense fuelExpense, long carId) {
        FuelExpense checkIfExpenseExistsExpense = getFuelExpense(fuelExpense.getId());
        Car car = carService.getCar(carId);
        fuelExpense.setCar(car);

        return repository.save(fuelExpense);
    }

    @Override
    public List<FuelExpense> getMonthlyTotalFuelExpenses() {
        long dayInMillis = 86400000;
        long timeInMillis = System.currentTimeMillis() - dayInMillis*30;

        List<FuelExpense> fuelExpenses = repository.findMonthlyTotalExpenses(new Timestamp(timeInMillis));

        if (fuelExpenses.size() == 0)
            throw new FuelExpenseNotFoundException("any within a month");
        else
            return fuelExpenses;
    }

    @Override
    public void deleteFuelExpense(long id) {
        FuelExpense fuelExpense = getFuelExpense(id);

        repository.delete(fuelExpense);
    }
}
