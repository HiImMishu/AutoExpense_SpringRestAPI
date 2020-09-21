package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.Engine;

public interface EngineInterface {

    public Engine getEngine(long id);
    public Engine saveEngine(Engine engine);
    public void deleteEngine(Engine engine);
}
