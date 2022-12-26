package com.example.newCustomers.controllers;

import com.example.newCustomers.dto.UserDto;
import com.example.newCustomers.models.User;
import com.example.newCustomers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        User user= userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto userDto = UserDto.fromUser(user);
        log.info("Пользователь с id:" + user.getId() + ", с именем: " + user.getName() + " - успешно найден!");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping
    List<User> getAll(){
       return userService.getAll();
    }
    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        return userService.update(user, id);
    }
}
