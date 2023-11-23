package com.wego.candidate_experience.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.models.Experience;
import com.wego.candidate_experience.repositories.CandidateRepository;
import com.wego.candidate_experience.repositories.ExperienceRepository;


@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepo;

    @Autowired
    ExperienceRepository experienceRepo;

    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = candidateRepo.findAll();
        return candidates;
    }

    public Candidate getCandidate(int id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        return candidate;
    }

    public List<Experience> getExperiences(int candidateId) {
        Boolean candidate_exists = this.candidateExists(candidateId);
        if (!candidate_exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<Experience> experiences = experienceRepo.findAllByCandidateId(candidateId);
        return experiences;
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public Candidate updateCandidate(int id,Candidate _candidate) {
        Candidate candidate = this.getCandidate(id);
        if (candidate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        candidate.setName(_candidate.getName() !=null ?_candidate.getName():candidate.getName());
        candidate.setPhoneNo(_candidate.getPhoneNo() != null ? _candidate.getPhoneNo() : candidate.getPhoneNo());
        candidate.setDob(_candidate.getDob() != null ? _candidate.getDob() : candidate.getDob());

        return candidateRepo.save(candidate);
    }

    public Boolean candidateExists(int id){
        return candidateRepo.existsById(id);
    }

}
