package com.example.newCustomers.error;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@Log4j
public class NotFoundException extends ResponseStatusException {
    public NotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Пользователь с таким id: " + id + " не найден!");
        log.error(HttpStatus.NOT_FOUND + " Пользователь с таким id: " + id + " не найден!");
    }
    public NotFoundException(String login) {
        super(HttpStatus.NOT_FOUND, "Пользователь с таким login: " + login + " не найден, или неверный пароль!");
        log.error(HttpStatus.NOT_FOUND + " Пользователь с таким login: " + login + " не найден, или неверный пароль!");
    }
    public NotFoundException(String name , boolean role) {
        super(HttpStatus.NOT_FOUND, "Role с таким name: " + name + " не найден!");
        log.error(HttpStatus.NOT_FOUND + " Role с таким name: " + name + " не найден!");
    }
}
