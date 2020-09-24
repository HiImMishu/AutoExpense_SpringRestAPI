package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.FuelExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface FuelExpenseRepository extends JpaRepository<FuelExpense, Long> {

    @Query(value = "SELECT f from FuelExpense f where f.id = :id and f.car.user.id = ?#{principal.claims['sub']}")
    public Optional<FuelExpense> findById(@Param("id") long id);

    @Query(value = "Select f from FuelExpense f where f.car.id = :carId and f.car.user.id = ?#{principal.claims['sub']}")
    public List<FuelExpense> findAllByCarId(long carId);

    @Query(value = "select f from FuelExpense f where f.expenseDate > :timestamp and f.car.user.id = ?#{principal.claims['sub']}")
    public List<FuelExpense> findMonthlyTotalExpenses(Timestamp timestamp);
}
