package com.example.webbancafev3.Repositories;

import com.example.webbancafev3.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
