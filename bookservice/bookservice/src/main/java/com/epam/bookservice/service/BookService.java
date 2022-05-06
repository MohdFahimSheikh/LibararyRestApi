package com.epam.bookservice.service;

import com.epam.bookservice.entity.Book;
import com.epam.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllbooks() {

        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    public Book addBook(Book book) {

        return bookRepository.save(book);
    }

    public void deleteBookById(int bookId) {

        bookRepository.deleteById(bookId);
    }

    public Book updateBookBYId(int bookId, Book book) {
        Book book1 = bookRepository.findById(bookId).get();
        book1.setBookName(book.getBookName());
        book1.setPublisher(book.getPublisher());
        book1.setAuthor(book.getAuthor());
        return bookRepository.save(book1);
    }


}
