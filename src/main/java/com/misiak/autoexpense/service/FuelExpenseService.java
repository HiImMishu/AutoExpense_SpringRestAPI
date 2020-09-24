package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.FuelExpense;

import java.util.List;

public interface FuelExpenseService {
    public FuelExpense getFuelExpense(long id);
    public List<FuelExpense> getFuelExpenses(long carId);
    public FuelExpense addFuelExpense(FuelExpense fuelExpense, long carId);
    public FuelExpense updateFuelExpense(FuelExpense fuelExpense, long carId);
    public List<FuelExpense> getMonthlyTotalFuelExpenses();
    public void deleteFuelExpense(long id);
}
