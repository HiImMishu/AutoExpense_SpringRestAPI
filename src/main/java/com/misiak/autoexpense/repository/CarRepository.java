package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select  c from Car c where c.user.id = ?#{principal.claims['sub']} and c.id = :id")
    public Optional<Car> findById(@Param("id") long id);

    @Query("select c from Car c where c.user.id = ?#{principal.claims['sub']}")
    public List<Car> findAll();
}
