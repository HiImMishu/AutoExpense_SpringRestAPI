package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Engine;
import com.misiak.autoexpense.repository.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EngineServiceImpl implements EngineService {

    private EngineRepository engineRepository;

    @Override
    public Engine getEngine(long id) {
        return engineRepository.findById(id).get();
    }

    @Override
    public Engine saveEngine(Engine engine) {
        return null;
    }

    @Override
    public void deleteEngine(Engine engine) {

    }
}
