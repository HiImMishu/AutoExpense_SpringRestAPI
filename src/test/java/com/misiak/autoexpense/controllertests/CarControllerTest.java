package com.misiak.autoexpense.controllertests;

import com.misiak.autoexpense.controller.CarController;
import com.misiak.autoexpense.repository.CarRepository;
import com.misiak.autoexpense.service.CarService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest({CarController.class, CarService.class})
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository repository;

    
}
