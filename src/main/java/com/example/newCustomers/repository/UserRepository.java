package com.example.newCustomers.repository;

import com.example.newCustomers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   public Optional<User> findByLogin(String login);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByPhone(String phone);
}
