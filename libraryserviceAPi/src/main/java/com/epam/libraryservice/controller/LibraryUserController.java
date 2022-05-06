package com.epam.libraryservice.controller;

import com.epam.libraryservice.entiity.LibraryDto;
import com.epam.libraryservice.entiity.UserDto;
import com.epam.libraryservice.exception.UserAlreadyHaveThisBook;
import com.epam.libraryservice.exception.UserAlreadyHaveThreeBooks;
import com.epam.libraryservice.service.LibraryUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryUserController {

    @Autowired
    LibraryUserService libraryUserService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("library/users")
    public List<UserDto> getAllUsersList() {
        return libraryUserService.getAllUsers();
    }

    @GetMapping("library/users/{username}")
    public UserDto getUserByUserName(@PathVariable("username") String username) {
        return libraryUserService.getUserByUserName(username);
    }

    @PostMapping("library/users")
    public String addUser(@RequestBody UserDto userDto) {
        libraryUserService.addUser(userDto);
        return "User added successfully";
    }

    @PutMapping("library/users/{username}")
    public void updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        libraryUserService.updateUser(username, userDto);
    }

    @DeleteMapping( "library/users/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        libraryUserService.deleteUser(username);
        return "User Deleted Successfully";
    }

    @PostMapping("library/users/{username}/books/{book_id}")
    public LibraryDto giveUserABook(@PathVariable("username") String username, @PathVariable("book_id") int bookId) throws UserAlreadyHaveThisBook, UserAlreadyHaveThreeBooks {
        return libraryUserService.giveUserABook(username, bookId);
    }

    @DeleteMapping("library/users/{username}/books/{book_id}")
    public String releaseBookFromUser(@PathVariable("username") String username, @PathVariable("book_id") int bookId) {
        libraryUserService.releaseBookFromUser(username, bookId);
        return "book deleted for user Successfully";
    }
}
