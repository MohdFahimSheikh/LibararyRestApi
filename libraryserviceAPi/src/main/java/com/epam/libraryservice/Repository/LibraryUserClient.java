package com.epam.libraryservice.Repository;

import com.epam.libraryservice.entiity.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name="users",url ="http://localhost:9005")
@FeignClient(name = "user-service")
@LoadBalancerClient(name="user-service")
public interface LibraryUserClient
{
    @GetMapping("/users")
    public List<UserDto> getAllUsers();

    @GetMapping("/users/{username}")
    public UserDto getUserByUserName(@PathVariable("username") String username);

    @PostMapping("/users")
    public String addUser(@RequestBody UserDto userDto);

    @DeleteMapping("users/{username}")
    public String deleteUser(@PathVariable("username") String username);

    @PutMapping("users/{username}")
    public String updateUser(@PathVariable("username") String username,@RequestBody UserDto userDto);

}
