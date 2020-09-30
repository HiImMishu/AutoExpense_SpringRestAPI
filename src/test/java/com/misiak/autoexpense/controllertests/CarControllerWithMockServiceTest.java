package com.misiak.autoexpense.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misiak.autoexpense.controller.CarController;
import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.service.CarService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({CarController.class})
public class CarControllerWithMockServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService mockCarService;

    @Test
    @WithMockUser
    public void shouldGetSavedCarWithActualCarId() throws Exception {
        Car car = new Car(3, "BMW", "5 Series", 2016, 46390, new BigDecimal("195000.00"), null, null, null);

        when(mockCarService.saveCar(ArgumentMatchers.any(Car.class))).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(3)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(8)));

        verify(mockCarService).saveCar(ArgumentMatchers.any(Car.class));
    }
}
