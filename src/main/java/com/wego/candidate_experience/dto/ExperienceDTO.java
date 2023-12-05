package com.wego.candidate_experience.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ExperienceDTO {
    private int id;

    private String title;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;

    public ExperienceDTO(int id, String title, String companyName, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
