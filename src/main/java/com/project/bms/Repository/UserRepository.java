package com.project.bms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);
}
