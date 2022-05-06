package com.epam.bookservice.controller;

import com.epam.bookservice.entity.Book;
import com.epam.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllbooks();
    }



    @GetMapping("/books/{book_id}")
    public Book getBookById(@PathVariable("book_id") int bookId)
    {
        return bookService.getBookById(bookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/books")
    public String addBook(@RequestBody Book book)
    {
         bookService.addBook(book);
        return "Book is created successfully";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/books/{book_id}")
    public String deleteBook(@PathVariable("book_id") int bookId)
    {
        bookService.deleteBookById(bookId);
        return "Book is deleted successsfully";

    }

    @PutMapping("/books/{book_id}")
    public String updateBook(@PathVariable("book_id") int bookId,@RequestBody Book book)

    {
         bookService.updateBookBYId(bookId,book);
         return "Book is updated successsfully";
    }

}
