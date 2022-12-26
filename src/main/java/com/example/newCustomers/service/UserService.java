package com.example.newCustomers.service;

import com.example.newCustomers.dto.RoleDto;
import com.example.newCustomers.models.User;

import java.util.List;

public interface UserService {
     public User register(User user);

    public List<User> getAll();

    public User findByLogin(String login);

    public User findById(Long id);

    public void delete(Long id);

    public User update(User user, Long id);

    public void addRole(Long id, RoleDto roleDto);
}
