package com.example.newCustomers.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleDto {
   @NotNull
    String role;
}
