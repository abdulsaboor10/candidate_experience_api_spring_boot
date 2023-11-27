package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.ExperienceDTO;
import com.wego.candidate_experience.models.Experience;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExperienceDTOMapper implements Function<Experience, ExperienceDTO> {
    @Override
    public ExperienceDTO apply(Experience e) {
        return new ExperienceDTO(
                e.getId(),
                e.getTitle(),
                e.getCompanyName(),
                e.getStartDate(),
                e.getEndDate()
        );
    }
}
