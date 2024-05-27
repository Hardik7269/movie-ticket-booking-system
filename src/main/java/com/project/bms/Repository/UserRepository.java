package com.project.bms.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUname(String username);
}
