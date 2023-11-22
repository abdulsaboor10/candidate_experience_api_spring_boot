package com.wego.candidate_experience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.candidate_experience.models.Experience;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Integer> {
    List<Experience> findAllByCustomerId(int experienceId);
}
