package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {
}
