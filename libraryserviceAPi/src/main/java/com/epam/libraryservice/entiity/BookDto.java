package com.epam.libraryservice.entiity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    private String publisher;
    private String author;
}
