package com.example.webbancafev3.Repositories;

import com.example.webbancafev3.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>
{
    Role findRoleById(Long id);
}
