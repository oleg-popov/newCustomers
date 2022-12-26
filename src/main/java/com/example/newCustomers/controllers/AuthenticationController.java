package com.example.newCustomers.controllers;

import com.example.newCustomers.dto.AuthenticationRequestDto;
import com.example.newCustomers.error.NotFoundException;
import com.example.newCustomers.models.User;
import com.example.newCustomers.security.jwt.JwtTokenProvider;
import com.example.newCustomers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        String login = authenticationRequestDto.getLogin();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, authenticationRequestDto.getPassword()));
        User user = userService.findByLogin(login);
        if (user == null) {
            throw new NotFoundException(login);
        }
        String token = jwtTokenProvider.createToken(login, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("login", login);
        response.put("token", token);
        log.info("Пользователь: " + user.getName() +  " найден под логином: " + "'" + login + "'");

        return ResponseEntity.ok(response);
    }
    @PostMapping("/save")
    public User save(@RequestBody User user) {
        user = userService.register(user);
        return user ;
    }
}
