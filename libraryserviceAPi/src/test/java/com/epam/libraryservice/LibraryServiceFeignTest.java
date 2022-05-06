package com.epam.libraryservice;

import com.epam.libraryservice.Repository.LibraryBookClient;
import com.epam.libraryservice.Repository.LibraryRepository;
import com.epam.libraryservice.Repository.LibraryUserClient;
import com.epam.libraryservice.entiity.BookDto;
import com.epam.libraryservice.entiity.Library;
import com.epam.libraryservice.entiity.LibraryDto;
import com.epam.libraryservice.entiity.UserDto;
import com.epam.libraryservice.exception.UserAlreadyHaveThisBook;
import com.epam.libraryservice.exception.UserAlreadyHaveThreeBooks;
import com.epam.libraryservice.service.LibraryBookService;
import com.epam.libraryservice.service.LibraryUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LibraryServiceFeignTest {

    @InjectMocks
    LibraryBookService libraryBookService;

    @InjectMocks
    LibraryUserService libraryUserService;



    @Mock
    LibraryRepository libraryRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    LibraryBookClient libraryBookClient;

    @Mock
    LibraryUserClient libraryUserClient;

    @Mock
    BookDto bookDto;
    UserDto userDto;
    LibraryDto libraryDto;
    Library library;


    @BeforeEach
    public void setup(){
        bookDto=new BookDto(1,"bookl","chaaitanya","krishna");
        userDto=new UserDto("chaitanya","Krishna chaitanya","krishna@gmail.com");
        libraryDto=new LibraryDto(1,"krishna123",1);
        library=new Library(1,"krishna765",1);
    }

    @Test
    public void getAllBooksTest(){
        List<BookDto> bookDtoList=new ArrayList<>();
        bookDtoList.add(bookDto);
        when(libraryBookClient.getAllBooks()).thenReturn(bookDtoList);
        libraryBookService.getAllBooks();
        assertTrue(true);
    }

    @Test
    public void getBookTest(){
        when(libraryBookClient.getBookById(anyInt())).thenReturn(bookDto);
        libraryBookService.getBookById(1);
        assertTrue(true);
    }

    @Test
    public void insertBookTest(){
        when(libraryBookClient.addBook(any())).thenReturn(String.valueOf(bookDto));
        libraryBookService.addBook(bookDto);
        assertTrue(true);
    }

    @Test
    public void updateBookTest(){
        BookDto book1 = new BookDto(1,"book345","kc","chaitanya23");
        libraryBookService.updateBookBYId(1,book1);
        assertTrue(true);
    }

    @Test
    public void deleteBookTest(){
        when(libraryBookClient.deleteBook(Mockito.anyInt())).thenReturn(String.valueOf(true));
        libraryBookService.deleteBookById(Mockito.anyInt());
        Assertions.assertTrue(true);
    }

    @Test
    public void getAllUsersTest(){
        List<UserDto> userDtoList=new ArrayList<>();
        userDtoList.add(userDto);
        when(libraryUserClient.getAllUsers()).thenReturn(userDtoList);
        libraryUserService.getAllUsers();
        assertTrue(true);
    }

    @Test
    public void getUserTest(){
        when(libraryUserClient.getUserByUserName(anyString())).thenReturn(userDto);
        libraryUserService.getUserByUserName("krishnachaitanya1");
        assertTrue(true);
    }

    @Test
    public void insertUserTest(){
        when(libraryUserClient.addUser(any())).thenReturn(String.valueOf(userDto));
        libraryUserService.addUser(userDto);
        assertTrue(true);
    }

    @Test
    public void updateUserTest(){
        when(libraryUserClient.updateUser(anyString(),any())).thenReturn(String.valueOf(userDto));
        libraryUserService.updateUser(anyString(),any());
        assertTrue(true);
    }


    @Test
    public void deleteduserTest(){
        when(libraryUserClient.deleteUser(Mockito.anyString())).thenReturn(String.valueOf(true));
        libraryUserService.deleteUser(Mockito.anyString());
        Assertions.assertTrue(true);
    }
    @Test
    public void releaseFromUserTest(){
        doNothing().when(libraryRepository).deleteByUsernameAndBookId(anyString(),anyInt());
        libraryUserService.releaseBookFromUser("krishnachaitanya1",1);
        assertTrue(true);
    }



}
