package com.wego.candidate_experience.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wego.candidate_experience.models.Candidate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExperienceWithCandidateDTO {
    private int id;

    private String title;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;

    private CandidateDTO candidate;

    public ExperienceWithCandidateDTO(int id, String title, String companyName, LocalDate startDate, LocalDate endDate, CandidateDTO candidate) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.candidate = candidate;
    }
}
