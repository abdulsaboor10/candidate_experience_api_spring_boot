package com.wego.candidate_experience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.candidate_experience.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    
}
