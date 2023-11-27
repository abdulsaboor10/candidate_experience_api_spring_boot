package com.wego.candidate_experience.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateWithRecentExperienceDTO {

    private int id;

    private String name;
    private LocalDate dob;
    private String phoneNo;
    private ExperienceDTO experience;

    public CandidateWithRecentExperienceDTO(int id, String name, LocalDate dob, String phoneNo, ExperienceDTO experience) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.experience = experience;
    }
}
