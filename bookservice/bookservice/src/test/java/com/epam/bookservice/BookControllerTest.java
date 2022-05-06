package com.epam.bookservice;

import com.epam.bookservice.controller.BookController;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    private Book book;


    @Test
    @DisplayName("get all Books Should return the list of books")
    public void getAllBooksTest() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("sk", "gfgfd", "fdgf"));
        books.add(new Book("sk2", "gfgfe2vdd", "ffdddgf"));
        when(bookService.getAllbooks()).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getBookById Should Return Book")
    public void getBookByIdTest() throws Exception {
        Book book = new Book("sff", "fdfd", "gdgg");
        when(bookService.getBookById(anyInt())).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books")
                .contentType(MediaType.APPLICATION_JSON).flashAttr("book", book))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addBook should add the book")
    public void addBookTest() throws Exception {
        Book book = new Book("sff", "fdfd", "gdgg");
        when(bookService.addBook(any(Book.class))).thenReturn(book);
        mockMvc.perform(post("/books").
                contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("deleteBook should delete the book")
    public void deleteBook() throws Exception {
        doNothing().when(bookService).deleteBookById(anyInt());
        mockMvc.perform(delete("/books/2").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());


    }


    @Test
    @DisplayName("UpdateBook should update the book details and return book ")
    public void updateBookTest() throws Exception {
        Book book = new Book("sff", "fdfd", "gdgg");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/books/1")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }
}

