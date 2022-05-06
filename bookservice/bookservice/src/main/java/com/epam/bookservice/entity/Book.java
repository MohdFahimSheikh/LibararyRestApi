package com.epam.bookservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    @Column(name="book_name")
    private String bookName;
    @Column(name="publisher")
    private String publisher;

    public Book(String bookName, String publisher, String author) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
    }

    @Column(name="author")
    private String author;
}
