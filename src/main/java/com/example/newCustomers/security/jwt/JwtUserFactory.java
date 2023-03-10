package com.example.newCustomers.security.jwt;

import com.example.newCustomers.models.Role;
import com.example.newCustomers.models.Status;
import com.example.newCustomers.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE)
        );
    }
    private static List<GrantedAuthority>mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                        ).collect(Collectors.toList());
    }
}
