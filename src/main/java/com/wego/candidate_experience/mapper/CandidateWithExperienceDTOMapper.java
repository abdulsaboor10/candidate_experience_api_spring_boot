package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.CandidateWithExperiencesDTO;
import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.mapper.ExperienceDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CandidateWithExperienceDTOMapper implements Function<Candidate , CandidateWithExperiencesDTO> {

    @Autowired
    ExperienceDTOMapper experienceDTOMapper;
    @Override
    public CandidateWithExperiencesDTO apply(Candidate candidate) {
        return new CandidateWithExperiencesDTO(
                candidate.getId(),
                candidate.getName(),
                candidate.getDob(),
                candidate.getPhoneNo(),
                candidate.getExperiences().stream().map(experienceDTOMapper).collect(Collectors.toList())
        );
    }
}
