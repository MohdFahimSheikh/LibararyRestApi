package com.epam.userentity.service;

import com.epam.userentity.entity.User;
import com.epam.userentity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserName(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String username) {

        userRepository.deleteByUsername(username);
    }

    public User updateUser(String username, User user) {
        User user1 = userRepository.findById(username).get();
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        return userRepository.save(user1);
    }
}
