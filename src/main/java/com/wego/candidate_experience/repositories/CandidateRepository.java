package com.wego.candidate_experience.repositories;

import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.candidate_experience.models.Candidate;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e " +
            "WHERE e.endDate = (SELECT MAX(e2.endDate) FROM Experience e2 WHERE e2.candidate = c) " +
            "OR e.endDate IS NULL")
    Page<Candidate> findAllCandidatesWithRecentExperience(Pageable pageable);

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e")
    Page<Candidate> findAllWithExperiences(Pageable pageable);
}
