package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.FuelExpense;
import com.misiak.autoexpense.service.FuelExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/cars/fuel-expenses")
@RestController
public class FuelExpenseController {

    private FuelExpenseService service;

    @GetMapping("/{id}")
    public FuelExpense getFuelExpense(@PathVariable long id) {
        return service.getFuelExpense(id);
    }

    @GetMapping
    public List<FuelExpense> getFuelExpenses(@RequestParam("car_id") long carId) {
        return service.getFuelExpenses(carId);
    }

    @GetMapping("/monthly")
    public List<FuelExpense> getMonthlyTotalFuelExpenses() {
        return service.getMonthlyTotalFuelExpenses();
    }

    @PostMapping
    public FuelExpense addFuelExpense(@RequestBody FuelExpense fuelExpense, @RequestParam("car_id") long carId) {
        return service.addFuelExpense(fuelExpense, carId);
    }

    @PutMapping
    public FuelExpense updateFuelExpense(@RequestBody FuelExpense fuelExpense, @RequestParam("car_id") long carId) {
        return service.updateFuelExpense(fuelExpense, carId);
    }

    @DeleteMapping("/{id}")
    public void deleteFuelExpense(@PathVariable long id) {
        service.deleteFuelExpense(id);
    }
}
