package com.epam.libraryservice.entiity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto
{

    private int id;
    private String username;
    private int bookId;
    public LibraryDto(String username,int bookId)
    {
        this.username=username;
        this.bookId=bookId;
    }
}
