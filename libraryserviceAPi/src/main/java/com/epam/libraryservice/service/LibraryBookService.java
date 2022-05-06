package com.epam.libraryservice.service;


import com.epam.libraryservice.Repository.LibraryBookClient;

import com.epam.libraryservice.entiity.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryBookService {

    @Autowired
    LibraryBookClient libraryBookClient;


    @Autowired
    ModelMapper modelMapper;

    public List<BookDto> getAllBooks()
    {
        return libraryBookClient.getAllBooks();
    }
    public BookDto getBookById(int bookId)
    {
        return libraryBookClient.getBookById(bookId);
    }
    public void addBook(BookDto bookDto)
    {
         libraryBookClient.addBook(bookDto);
    }
    public void deleteBookById(int bookId)
    {
         libraryBookClient.deleteBook(bookId);
    }
    public void updateBookBYId(int bookId, BookDto bookDto)
    {
        libraryBookClient.updateBook(bookId,bookDto);
    }

}
