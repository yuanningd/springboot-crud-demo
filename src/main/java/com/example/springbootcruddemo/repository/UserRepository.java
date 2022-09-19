package com.example.springbootcruddemo.repository;

import com.example.springbootcruddemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
