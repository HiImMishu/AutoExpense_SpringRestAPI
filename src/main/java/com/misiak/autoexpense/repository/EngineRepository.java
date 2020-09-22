package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EngineRepository extends JpaRepository<Engine, Long> {

    @Query(value = "SELECT e from Engine e where e.id = :id and e.id in (select c.engine.id from Car c where c.user.id = ?#{principal.claims['sub']})")
    public Optional<Engine> findById(@Param("id") long id);
}
