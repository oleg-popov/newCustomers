package com.example.newCustomers.error;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Log4j
public class EmailIsAlreadyExistsException extends ResponseStatusException {
    public EmailIsAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Email Error: Пользователь с таким почтовым адресом уже существует!");
        log.error(HttpStatus.CONFLICT + " Email Error: Пользователь с таким почтовым адресом уже существует!");
    }
}
