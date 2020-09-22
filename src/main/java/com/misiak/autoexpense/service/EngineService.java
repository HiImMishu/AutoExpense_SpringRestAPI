package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Engine;

public interface EngineService {
    public Engine getEngine(long id);
    public Engine saveEngine(Engine engine, long carId);
    public Engine updateEngine(Engine engine, long carId);
}
