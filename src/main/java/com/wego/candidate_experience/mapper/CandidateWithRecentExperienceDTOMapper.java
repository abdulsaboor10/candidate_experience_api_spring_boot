package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.models.Experience;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class CandidateWithRecentExperienceDTOMapper implements Function<Candidate, CandidateWithRecentExperienceDTO> {
    @Override
    public CandidateWithRecentExperienceDTO apply(Candidate candidate) {
        List<Experience> experiences = new ArrayList<>(candidate.getExperiences());
        ExperienceDTO experience;
        if (experiences.size() > 0){
            Experience recent_experience  = experiences.get(0);
            experience = new ExperienceDTO(
                    recent_experience.getId(),
                    recent_experience.getTitle(),
                    recent_experience.getCompanyName(),
                    recent_experience.getStartDate(),
                    recent_experience.getEndDate()
            );
        }else{
            experience = null;
        }

        return new CandidateWithRecentExperienceDTO(
                candidate.getId(),
                candidate.getName(),
                candidate.getDob(),
                candidate.getPhoneNo(),
                experience
        );
    }
}
