package com.epam.userentity;

import com.epam.userentity.controller.UserController;
import com.epam.userentity.entity.User;
import com.epam.userentity.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("get all user Should return the list of books")
    public void getAllBooksTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("sk", "gfgfd", "fdgf"));
        users.add(new User("sk2", "gfgfe2vdd", "ffdddgf"));
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUserByName Should Return User")
    public void getBookByNameTest() throws Exception {
        User user = new User("sff", "fdfd", "gdgg");
        when(userService.getUserByUserName(anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/fahim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addUser should add the User")
    public void addBookTest() throws Exception {
        User user = new User("sff", "fdfd", "gdgg");
        when(userService.addUser(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("deleteBook should delete the book")
    public void deleteBook() throws Exception {
        doNothing().when(userService).deleteUser(anyString());
        mockMvc.perform(delete("/users/2").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }

    @Test
    @DisplayName("UpdateBook should update the book details and return book ")
    public void updateBookTest() throws Exception {
        User user = new User("sff", "fdfd", "gdgg");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/users/1")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }

}
