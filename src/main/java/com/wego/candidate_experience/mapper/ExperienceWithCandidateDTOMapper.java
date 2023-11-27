package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.ExperienceWithCandidateDTO;
import com.wego.candidate_experience.models.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExperienceWithCandidateDTOMapper implements Function<Experience , ExperienceWithCandidateDTO> {
    @Override
    public ExperienceWithCandidateDTO apply(Experience experience) {


        CandidateDTO candidate = new CandidateDTO(
                experience.getCandidate().getId(),
                experience.getCandidate().getName(),
                experience.getCandidate().getDob(),
                experience.getCandidate().getPhoneNo()
        );


        return new ExperienceWithCandidateDTO(
                experience.getId(),
                experience.getTitle(),
                experience.getCompanyName(),
                experience.getStartDate(),
                experience.getEndDate(),
                candidate
        );

        //TODO:: use builder
    }
}
