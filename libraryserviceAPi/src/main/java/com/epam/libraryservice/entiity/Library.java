package com.epam.libraryservice.entiity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="librarynew")
public class Library
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="bookId")
    private int bookId;
    public Library(String username,int bookId)
    {
        this.username=username;
        this.bookId=bookId;
    }
}
