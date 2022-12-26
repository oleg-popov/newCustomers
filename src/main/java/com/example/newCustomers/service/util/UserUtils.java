package com.example.newCustomers.service.util;

import com.example.newCustomers.error.EmailIsAlreadyExistsException;
import com.example.newCustomers.error.PhoneIsAlreadyExistsException;
import com.example.newCustomers.models.User;
import com.example.newCustomers.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUtils {
    private UserRepository userRepository;

    public User updateUser(User user, Long id){
        User u = userRepository.findById(id).get();
        if (user.getName() != null) u.setName(user.getName());
        if (user.getPhone() != null) u.setPhone(user.getPhone());
        if (user.getEmail() != null) u.setEmail(user.getEmail());
        return u;
    }
    public void chackEmail(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EmailIsAlreadyExistsException();
        }
    }
    public void chackEmail(User user, Long id){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            User u = userRepository.findByEmail(user.getEmail()).get();
            if (u.getId() != id){
                throw new EmailIsAlreadyExistsException();
            }
        }
    }
    public void checkPhoneNumber(User user){
        if (userRepository.findByPhone(user.getPhone()).isPresent()){
            throw new PhoneIsAlreadyExistsException();
        }
    }
    public void checkPhoneNumber(User user, Long id){
        if (userRepository.findByPhone(user.getPhone()).isPresent()){
            User u = userRepository.findByPhone(user.getPhone()).get();
            if (u.getId() != id){
                throw new PhoneIsAlreadyExistsException();
            }
        }
    }
}
