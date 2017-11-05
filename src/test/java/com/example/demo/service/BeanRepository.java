package com.example.demo.service;

import com.example.demo.domain.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Burkhard Graves
 */
public interface BeanRepository extends JpaRepository<Bean, Integer> {
    
    Bean findByName(String name);
}
