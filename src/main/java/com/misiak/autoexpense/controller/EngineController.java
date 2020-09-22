package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.Engine;
import com.misiak.autoexpense.service.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/cars/engines")
@RestController
public class EngineController {

    private EngineService engineService;

    @GetMapping("/{id}")
    public Engine getEngine(@PathVariable long id) {
        return engineService.getEngine(id);
    }
}
