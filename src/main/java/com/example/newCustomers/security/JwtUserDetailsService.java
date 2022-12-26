package com.example.newCustomers.security;

import com.example.newCustomers.models.User;
import com.example.newCustomers.security.jwt.JwtUser;
import com.example.newCustomers.security.jwt.JwtUserFactory;
import com.example.newCustomers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByLogin(name);
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
