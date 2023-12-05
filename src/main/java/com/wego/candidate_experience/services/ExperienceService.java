package com.wego.candidate_experience.services;

import java.util.List;
import java.util.stream.Collectors;

import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.dto.ExperienceWithCandidateDTO;
import com.wego.candidate_experience.mapper.ExperienceDTOMapper;
import com.wego.candidate_experience.mapper.ExperienceWithCandidateDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.models.Experience;
import com.wego.candidate_experience.repositories.CandidateRepository;
import com.wego.candidate_experience.repositories.ExperienceRepository;

@Service
public class ExperienceService {

    @Autowired
    ExperienceRepository experienceRepo;

    @Autowired
    CandidateRepository candidateRepo;

    @Autowired
    ExperienceDTOMapper experienceDTOMapper;

    @Autowired
    ExperienceWithCandidateDTOMapper experienceWithCandidateDTOMapper;

    public List<ExperienceDTO> getAllExperiences() {
        return experienceRepo.findAll(Sort.by(Sort.Direction.ASC, "startDate")).stream()
                .map(experienceDTOMapper).collect(Collectors.toList());
    }
    public List<ExperienceWithCandidateDTO> getAllExperiencesWithCandidate() {
        return experienceRepo.findAll().stream()
                .map(experienceWithCandidateDTOMapper).collect(Collectors.toList());
    }

    public ExperienceDTO getExperience(@PathVariable int id) {
        return experienceRepo.findById(id).map(experienceDTOMapper).orElse(null);
    }

    public Experience createExperience(int candidateId, Experience experience) {

        Candidate candidate = candidateRepo.findById(candidateId).orElse(null);
        if (candidate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        experience.setCandidate(candidate);
        return experienceRepo.save(experience);

    }

    public Experience updateExperience(int id,Experience experienceData) {
        Experience experience = experienceRepo.findById(id).orElse(null);
        if (experience == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        experience.setCompanyName(experienceData.getCompanyName() != null ? experienceData.getCompanyName()
                : experience.getCompanyName());
        experience.setTitle(experienceData.getTitle() != null ? experienceData.getTitle() : experience.getTitle());
        experience.setStartDate(experienceData.getStartDate() != null ? experienceData.getStartDate()
                : experience.getStartDate());
        experience.setEndDate(
                experienceData.getEndDate() != null ? experienceData.getEndDate() : experience.getEndDate());

        return experienceRepo.save(experience);
    }

}
