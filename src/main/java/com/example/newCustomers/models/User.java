package com.example.newCustomers.models;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Log4j
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "\\+380\\d{9}$", message = "Некорректный номер!")
    @Column(name = "phone")
    private String phone;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
