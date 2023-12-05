package com.wego.candidate_experience.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateDTO {
    private int id;

    private String name;
    private LocalDate dob;
    private String phoneNo;

    public CandidateDTO(int id, String name, LocalDate dob, String phoneNo) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNo = phoneNo;
    }
}
