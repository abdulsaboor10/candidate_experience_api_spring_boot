package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.models.Candidate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CandidateDTOMapper implements Function<Candidate , CandidateDTO> {
    @Override
    public CandidateDTO apply(Candidate c) {
        return new CandidateDTO(
                c.getId(),
                c.getName(),
                c.getDob(),
                c.getPhoneNo()
        );
    }
}
