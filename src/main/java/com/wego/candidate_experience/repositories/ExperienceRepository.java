package com.wego.candidate_experience.repositories;

import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.dto.ExperienceWithCandidateDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.candidate_experience.models.Experience;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Integer> {
    List<Experience> findAllByCandidateIdOrderByStartDateAsc(int candidateId);

    @Query("SELECT e FROM Experience e JOIN FETCH e.candidate")
    List<ExperienceWithCandidateDTO> findAllExperiencesWithCandidates();
}
