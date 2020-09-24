package com.misiak.autoexpense.controllertests;

import com.misiak.autoexpense.controller.UserController;
import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.repository.UserRepository;
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

import java.sql.Timestamp;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({UserController.class, UserService.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired UserService userService;

    @MockBean
    private UserRepository repository;

    @Test
    public void shouldNotGetUserWhenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void shouldGetUserWithIdFromAuthentiaction() throws Exception {
        when(repository.findById("user")).thenReturn(Optional.of(new User("id", "email", "firstName", "lastName", new Timestamp(System.currentTimeMillis()))));

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("\"id\":\"id\",\"email\":\"email\"")));

        verify(repository).findById("user");
    }

    @Test
    @WithMockUser
    public void shouldGet404WhenUserNotSignedUp() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("User not found with id: ")));

        verify(repository).findById("user");
    }
}
