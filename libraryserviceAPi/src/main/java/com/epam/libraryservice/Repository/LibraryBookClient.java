package com.epam.libraryservice.Repository;

import com.epam.libraryservice.entiity.BookDto;
import com.epam.libraryservice.entiity.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name="books",url ="http://localhost:9004")
@FeignClient(name = "book-service")
@LoadBalancerClient(name= "book-service")
public interface LibraryBookClient
{
    @GetMapping("/books")
    public List<BookDto> getAllBooks();

    @GetMapping("/books/{book_id}")
    public BookDto getBookById(@PathVariable("book_id") int bookId);

    @PostMapping("/books")
    public String addBook(@RequestBody BookDto bookDto);

    @DeleteMapping("/books/{book_id}")
    public String deleteBook(@PathVariable("book_id") int bookId);

    @PutMapping("/books/{book_id}")
    public String updateBook(@PathVariable("book_id") int bookId,@RequestBody BookDto bookDto);

}
