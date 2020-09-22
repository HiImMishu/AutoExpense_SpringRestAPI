package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.Engine;
import com.misiak.autoexpense.service.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/cars/engines")
@RestController
public class EngineController {

    private EngineService engineService;

    @GetMapping("/{id}")
    public Engine getEngine(@PathVariable long id) {
        return engineService.getEngine(id);
    }

    @PostMapping
    public Engine saveEngne(@RequestBody Engine engine, @RequestParam("car_id") long carId) {
        return engineService.saveEngine(engine, carId);
    }

    @PutMapping
    public Engine updateEngine(@RequestBody Engine engine, @RequestParam("car_id") long carId) {
        return engineService.updateEngine(engine, carId);
    }
}
