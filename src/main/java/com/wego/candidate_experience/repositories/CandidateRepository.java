package com.wego.candidate_experience.repositories;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.candidate_experience.models.Candidate;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e " +
            "WHERE e.endDate = (SELECT MAX(e2.endDate) FROM Experience e2 WHERE e2.candidate = c) " +
            "OR e.endDate IS NULL")
    Page<Candidate> findAllCandidatesWithRecentExperience(Pageable pageable);

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e")
    Page<Candidate> findAllWithExperiences(Pageable pageable);

    Page<Candidate> findAllByNameContainingIgnoreCase(Pageable pageable , String name);

    Page<Candidate> findAllByExperiencesCompanyName(Pageable pageable , String companyName);

    @Query("SELECT c FROM Candidate c LEFT JOIN c.experiences e WHERE c.name LIKE %:searchTerm% OR e.companyName LIKE %:searchTerm%")
    Page<Candidate> findByCandidateNameOrCompanyName(Pageable pageable, String searchTerm);

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e " +
            "WHERE c.id = :id " +
            "AND (e.endDate = (SELECT MAX(e2.endDate) FROM Experience e2 WHERE e2.candidate = c) " +
            "OR e.endDate IS NULL)")
    Optional<Candidate> findCandidateWithRecentExperience(int id);

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences e "+
            "WHERE c.id = :id")
    Optional<Candidate> findCandidateWithAllExperiences(int id);
}
