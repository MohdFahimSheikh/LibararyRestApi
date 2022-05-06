package com.epam.libraryservice;

import com.epam.libraryservice.Repository.LibraryBookClient;
import com.epam.libraryservice.Repository.LibraryRepository;
import com.epam.libraryservice.Repository.LibraryUserClient;
import com.epam.libraryservice.controller.LibraryBookController;
import com.epam.libraryservice.entiity.BookDto;
import com.epam.libraryservice.entiity.UserDto;
import com.epam.libraryservice.service.LibraryBookService;
import com.epam.libraryservice.service.LibraryUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(LibraryBookController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest
public class LibraryControllerFeignTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private LibraryBookService libraryBookService;
    @MockBean
    private LibraryBookClient libraryBookClient;
    @MockBean
    private LibraryRepository libraryRepository;
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;
    @MockBean
    private LibraryUserService libraryUserService;
    @MockBean
    private LibraryUserClient libraryUserClient;



    @org.junit.Test
    public void toGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @org.junit.Test
    @DisplayName(" Get book by id")
    public void toGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/books/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.Test
    @DisplayName("Create new book")
    public void toCreateNewBook() throws Exception {
        BookDto bookDto = new BookDto(1,"bookkc","chaitanya","authorchaitanya");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/books")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @org.junit.Test
    @DisplayName("Delete book by id")
    public void toDeleteBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update book by id")
    public void toUpdateBookById() throws Exception {
        BookDto bookDto = new BookDto(2,"bookkc","chaitanya","kc");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/library/books/2")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("To get all users")
    public void toGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(" To get user by username")
    public void toGetUserByUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/users/krishnachaitanya1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To create new user")
    public void toCreateNewUser() throws Exception {
        UserDto userDto = new UserDto("krishnachaitanya1","krishna567@gmail.com","chaitanya123");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/users")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }



    @Test
    @DisplayName("To delete user by username")
    public void shouldTestURIMappingToDeleteUserByUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/users/krishnachaitanya1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(" To update user by username")
    public void shouldTestURIMappingToUpdateUserByUsername() throws Exception {
        UserDto userDto = new UserDto("krishnachaitanya1","krishna9@gmail.com","krishnappp");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/library/users/krishnachaitanya1")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To issue book")
    public void ToIssueNewBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/users/krishnachaitanya1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To release book")
    public void ToReleaseBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/users/krishnachaitanya/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}