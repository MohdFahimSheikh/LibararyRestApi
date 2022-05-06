package com.epam.userentity.controller;

import com.epam.userentity.entity.User;
import com.epam.userentity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByUserName(@PathVariable("username") String username)
    {
         return userService.getUserByUserName(username);

    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user)
    {
         userService.addUser(user);
         return "User is created successfully";
    }

    @DeleteMapping("users/{username}")
    public String deleteUser(@PathVariable("username") String username)
   {
       userService.deleteUser(username);
       return "User is deleted successfully";
   }

    @PutMapping("users/{username}")
    public String updateUser(@PathVariable("username") String username,@RequestBody User user) {
        userService.updateUser(username,user);
        return "User is updated successfully";
    }
}
