package com.example.newCustomers.controllers;

import com.example.newCustomers.dto.RoleDto;
import com.example.newCustomers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/")
public class AdminController {
    private final UserService userService;
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id){
        userService.delete(id);
    }

    @PutMapping("/addRole/{id}")
    public void addRoleByid (@PathVariable Long id , @Valid @RequestBody RoleDto roleDto ){
        userService.addRole(id , roleDto);
    }
}
