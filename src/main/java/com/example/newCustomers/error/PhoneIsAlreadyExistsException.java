package com.example.newCustomers.error;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@Log4j
public class PhoneIsAlreadyExistsException extends ResponseStatusException {

    public PhoneIsAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Phone Error: Пользователь с таким номером телефона уже существует!");
        log.error(HttpStatus.CONFLICT + " Phone Error: Пользователь с таким номером телефона уже существует!");
    }
}
