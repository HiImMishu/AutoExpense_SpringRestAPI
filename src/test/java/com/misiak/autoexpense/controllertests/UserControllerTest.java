package com.misiak.autoexpense.controllertests;

import com.misiak.autoexpense.controller.UserController;
import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldNotGetUserWhenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void shouldGetUserWithIdFromAuthentiaction() throws Exception {
        when(userService.getUser("user")).thenReturn(java.util.Optional.of(new User("id", "email", "firstName", "lastName", new Timestamp(System.currentTimeMillis()))));

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("\"id\":\"id\",\"email\":\"email\"")));

        verify(userService).getUser("user");
    }

    @Test
    @WithMockUser
    public void shouldGet404WhenUserNotSignedUp() throws Exception {
        when(userService.getUser("user")).thenReturn(java.util.Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("ser not found with id: ")));

        verify(userService).getUser("user");
    }
}
