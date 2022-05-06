package com.epam.libraryservice.controller;

import com.epam.libraryservice.entiity.BookDto;
import com.epam.libraryservice.service.LibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryBookController {


    @Autowired
    LibraryBookService libraryBookService;

    @GetMapping("library/books")
    public List<BookDto> getAllBooksList()
    {
        return libraryBookService.getAllBooks();
    }

    @GetMapping("library/books/{book_id}")
    public BookDto getBookById(@PathVariable("book_id") int bookId) {
        return libraryBookService.getBookById(bookId);
    }


    @PostMapping("library/books")
    public String addBook(@RequestBody BookDto bookDto)
    {
        libraryBookService.addBook(bookDto);
        return "book added successfully";
    }


    @DeleteMapping("library/books/{book_id}")
    public String deleteBookById(@PathVariable("book_id") int bookId) {

         libraryBookService.deleteBookById(bookId);
        return "book deleted successfully";
    }

    @PutMapping("library/books/{book_id}")
    public String updateBookBYId(@PathVariable("book_id") int bookId, @RequestBody BookDto bookDto) {
        libraryBookService.updateBookBYId(bookId,bookDto);
        return "book updated successfully";
    }
}
