package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.FuelExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelExpenseRepository extends JpaRepository<FuelExpense, Long> {
}
