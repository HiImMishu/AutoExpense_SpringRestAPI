package com.misiak.autoexpense.controllertests;

import com.misiak.autoexpense.controller.CarController;
import com.misiak.autoexpense.entity.Car;
import com.misiak.autoexpense.repository.CarRepository;
import com.misiak.autoexpense.service.CarService;
import com.misiak.autoexpense.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({CarController.class, CarService.class})
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockBean
    private UserService userService;

    @MockBean
    private CarRepository repository;

    @Test
    public void shouldNotGetResourceWhenNotAuthenticated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void shouldGet404WhenUserHasNoCarWithGivenId() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Car not found with id - 1")));

        verify(repository).findById(1);
    }

    @Test
    @WithMockUser
    public void shouldGetCarWhenUserHasCarWithGivenId() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.of(new Car(1, "BMW", "Series 5", 2016, 26322.83, new BigDecimal("243564.54"), null, null, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("\"id\":1,\"make\":\"BMW\"")));

        verify(repository).findById(1);
    }

    @Test
    @WithMockUser
    public void shouldGet404WhenUserHasNoCars() throws Exception {
        when(repository.findAll()).thenReturn(new ArrayList<Car>());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Car not found with id - any")));

        verify(repository).findAll();
    }

    @Test
    @WithMockUser
    public void shouldGetListOfUserCars() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "BMW", "Series 5", 2016, 26322.83, new BigDecimal("243564.54"), null, null, null));
        cars.add(new Car(2, "Audi", "A5", 2016, 26322.83, new BigDecimal("243564.54"), null, null, null));


        when(repository.findAll()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("\"id\":1,\"make\":\"BMW\"")))
                .andExpect(content().string(containsString("\"id\":2,\"make\":\"Audi\"")));

        verify(repository).findAll();
    }
}
