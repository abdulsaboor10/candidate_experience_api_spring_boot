package com.wego.candidate_experience.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Experience> getAllExperiences() {
        List<Experience> experiences = experienceRepo.findAll();

        return experiences;
    }

    public Experience getExperience(@PathVariable int id) {
        Experience experience = experienceRepo.findById(id).orElse(null);
        return experience;
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
        Experience experience = this.getExperience(id);
        if (experience == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        experience.setCompany_name(experienceData.getCompany_name() != null ? experienceData.getCompany_name()
                : experience.getCompany_name());
        experience.setTitle(experienceData.getTitle() != null ? experienceData.getTitle() : experience.getTitle());
        experience.setStart_date(experienceData.getStart_date() != null ? experienceData.getStart_date()
                : experience.getStart_date());
        experience.setEnd_date(
                experienceData.getEnd_date() != null ? experienceData.getEnd_date() : experience.getEnd_date());

        return experienceRepo.save(experience);
    }

}
