package com.example.springbootcruddemo.repository;

import com.example.springbootcruddemo.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
