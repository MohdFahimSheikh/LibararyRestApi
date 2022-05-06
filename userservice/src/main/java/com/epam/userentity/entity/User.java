package com.epam.userentity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usernew")
public class User {

    @Id
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="name")
    private String name;


}
