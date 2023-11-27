package com.wego.candidate_experience.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateWithExperiencesDTO {
    private int id;

    private String name;
    private LocalDate dob;

    public CandidateWithExperiencesDTO(int id, String name, LocalDate dob, String phoneNo, List<ExperienceDTO> experiences) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.experiences = experiences;
    }

    private String phoneNo;

    private List<ExperienceDTO> experiences;
}
