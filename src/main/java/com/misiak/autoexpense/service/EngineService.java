package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Engine;

import java.util.Optional;

public interface EngineService {
    public Engine getEngine(long id);
    public Engine saveEngine(Engine engine);
    public void deleteEngine(Engine engine);
}
