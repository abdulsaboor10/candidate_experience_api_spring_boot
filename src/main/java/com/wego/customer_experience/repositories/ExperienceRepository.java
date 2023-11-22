package com.wego.customer_experience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.customer_experience.models.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Integer> {
    
}
