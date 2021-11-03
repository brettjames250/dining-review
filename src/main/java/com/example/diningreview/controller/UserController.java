package com.example.diningreview.controller;

import com.example.diningreview.model.User;
import com.example.diningreview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();

    }

    @GetMapping("/users/{userName}")
    public Optional<User> getUserByUsername(@PathVariable("userName") String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist");
        }
        return userRepository.findByUserName(userName);
    }


    @PutMapping("/users/{userName}")
    public User updateUser(@PathVariable("userName") String userName, @RequestBody User userChanges) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This use does not exist"));

        user.setCity(userChanges.getCity());
        user.setState(userChanges.getState());
        user.setPostCode(userChanges.getPostCode());
        user.setPeanutAllergy(userChanges.isPeanutAllergy());
        user.setEggAllergy(userChanges.isEggAllergy());
        user.setDairyAllergy(userChanges.isDairyAllergy());

        return userRepository.save(user);
    }

}
