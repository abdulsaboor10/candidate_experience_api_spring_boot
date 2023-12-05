package com.wego.candidate_experience.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.CandidateWithExperiencesDTO;
import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.mapper.CandidateDTOMapper;
import com.wego.candidate_experience.mapper.CandidateWithExperienceDTOMapper;
import com.wego.candidate_experience.mapper.CandidateWithRecentExperienceDTOMapper;
import com.wego.candidate_experience.mapper.ExperienceDTOMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    CandidateDTOMapper candidateDTOMapper;

    @Autowired
    ExperienceDTOMapper experienceDTOMapper;

    @Autowired
    CandidateWithExperienceDTOMapper candidateWithExperiencesDTOMapper;

    @Autowired
    CandidateWithRecentExperienceDTOMapper candidateWithRecentExperienceDTOMapper;



    public CandidateDTO getCandidate(int id) {
        Optional<Candidate> candidate = candidateRepo.findById(id);
        if (candidate.isPresent()){
            return candidate.map(candidateDTOMapper).orElse(null);
        }
        return null;
    }
    public Page<CandidateDTO> searchCandidates(String query,int offset , int pageSize) {
//        return candidateRepo.findAllByNameContainingIgnoreCase(PageRequest.of(offset,pageSize),query).map(candidateDTOMapper);
        return candidateRepo.findByCandidateNameOrCompanyName(PageRequest.of(offset,pageSize),query).map(candidateDTOMapper);
    }

    public List<ExperienceDTO> getExperiences(int candidateId) {
        Boolean candidate_exists = this.candidateExists(candidateId);
        if (!candidate_exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<ExperienceDTO> experiences = experienceRepo.findAllByCandidateIdOrderByStartDateAsc(candidateId)
                .stream().map(experienceDTOMapper).collect(Collectors.toList());
        return experiences;
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public Candidate updateCandidate(int id,Candidate _candidate) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if (candidate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        candidate.setName(_candidate.getName() !=null ?_candidate.getName():candidate.getName());
        candidate.setPhoneNo(_candidate.getPhoneNo() != null ? _candidate.getPhoneNo() : candidate.getPhoneNo());
        candidate.setDob(_candidate.getDob() != null ? _candidate.getDob() : candidate.getDob());

        return candidateRepo.save(candidate);
    }

    public Page<CandidateDTO> getAllCandidates(String sortBy, int offset, int pageSize , Boolean desc){
        Sort sort = desc ?  Sort.by(Sort.Order.desc(sortBy)) : Sort.by(sortBy);
        return candidateRepo.findAll(PageRequest.of(offset , pageSize).withSort(sort))
                .map(candidateDTOMapper);
    }

    public Page<CandidateWithExperiencesDTO> getAllCandidatesWithExperience(String sortBy, int offset, int pageSize, Boolean desc){
        Sort sort = desc ?  Sort.by(Sort.Order.desc(sortBy)) : Sort.by(sortBy);
        return candidateRepo.findAllWithExperiences(PageRequest.of(offset , pageSize).withSort(sort)).map(candidateWithExperiencesDTOMapper);
    }
    public Page<Candidate> getAllCandidatesWithRecentExperience(String sortBy, int offset, int pageSize , Boolean desc){
        Sort sort = desc ?  Sort.by(Sort.Order.desc(sortBy)) : Sort.by(sortBy);
        return candidateRepo.findAllCandidatesWithRecentExperience(PageRequest.of(offset , pageSize).withSort(sort));
    }
    public CandidateWithRecentExperienceDTO getCandidateWithRecentExperience(int id){
        Optional<Candidate> candidates = candidateRepo.findCandidateWithRecentExperience((id));
        Candidate candidate = candidates.get();
        return candidateWithRecentExperienceDTOMapper.apply(candidate);
    }
    public CandidateWithExperiencesDTO getCandidateWithAllExperiences(int id){
        Optional<Candidate> candidates = candidateRepo.findCandidateWithAllExperiences((id));
        return candidateWithExperiencesDTOMapper.apply(candidates.get());
    }
    public Boolean deleteCandidate(int id){
        candidateRepo.deleteById((id));
        return true;
    }
    public Boolean candidateExists(int id){
        return candidateRepo.existsById(id);
    }


}
