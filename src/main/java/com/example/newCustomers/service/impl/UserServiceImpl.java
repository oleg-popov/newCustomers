package com.example.newCustomers.service.impl;

import com.example.newCustomers.dto.RoleDto;
import com.example.newCustomers.error.NotFoundException;
import com.example.newCustomers.models.Role;
import com.example.newCustomers.models.Status;
import com.example.newCustomers.models.User;
import com.example.newCustomers.repository.RoleRepository;
import com.example.newCustomers.repository.UserRepository;
import com.example.newCustomers.service.UserService;
import com.example.newCustomers.service.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserUtils userUtils;

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("USER").get();
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        userUtils.chackEmail(user);
        userUtils.checkPhoneNumber(user);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("Добавлен новый пользователь: " + registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User>result = userRepository.findAll();
        log.info("Найдено пользователей: " + result.size());
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(()->{
            throw new NotFoundException("Логин: "  + login + " не найден");
        });
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->
             new NotFoundException(id));
    }
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
        {throw new NotFoundException(id);
        });
        user.setStatus(Status.DELETED);
        log.info("Пользователь с id: " + id + " - удалён!");
        userRepository.save(user);
    }
    @Override
    public User update(User user, Long id){
        userUtils.chackEmail(user,id);
        userUtils.checkPhoneNumber(user,id);
        user = userUtils.updateUser(user,id);
        log.info("Данные пользователя " + user.getId() + " - изменены!");
        return userRepository.save(user);
    }

    @Override
    public void addRole(Long id, RoleDto roleDto){
        Role role = roleRepository.findByName(roleDto.getRole()).orElseThrow(()->new NotFoundException(roleDto.getRole() , true));
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        user.getRoles().add(role);
        log.info("Пользователю с id: " + id + " - добалены права 'ADMIN'");
        userRepository.save(user);
    }
}
