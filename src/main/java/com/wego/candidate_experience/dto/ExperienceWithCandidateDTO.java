package com.wego.candidate_experience.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wego.candidate_experience.models.Candidate;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExperienceWithCandidateDTO {
    private int id;

    private String title;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;

    private Candidate candidate;
}
