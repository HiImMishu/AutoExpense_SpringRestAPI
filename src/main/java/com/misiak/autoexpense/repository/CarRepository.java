package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select  c from Car c where c.user.id = ?#{principal.claims['sub']}")
    public Optional<Car> findById(long id);
}
