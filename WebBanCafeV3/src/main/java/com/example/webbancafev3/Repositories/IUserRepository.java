package com.example.webbancafev3.Repositories;

import com.example.webbancafev3.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String>
{
    Optional<User> findByUsername(String username);
}
