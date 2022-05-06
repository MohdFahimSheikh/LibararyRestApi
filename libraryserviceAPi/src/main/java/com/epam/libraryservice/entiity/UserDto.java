package com.epam.libraryservice.entiity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    @Id
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="name")
    private String name;


}