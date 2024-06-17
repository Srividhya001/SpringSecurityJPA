package com.example.SpringSecurityJPA.repository;

import com.example.SpringSecurityJPA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<User,Integer> {
     Optional<User> findByUserName(String username);

}
