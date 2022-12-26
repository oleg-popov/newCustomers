package com.example.newCustomers.dto;

import com.example.newCustomers.models.Role;
import com.example.newCustomers.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    private List<Role> role;

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles());
        return userDto;
    }
}
