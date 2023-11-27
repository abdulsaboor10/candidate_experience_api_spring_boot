package com.wego.candidate_experience.mapper;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import com.wego.candidate_experience.models.Candidate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CandidateWithRecentExperienceDTOMapper implements Function<Candidate, CandidateWithRecentExperienceDTO> {
    @Override
    public CandidateWithRecentExperienceDTO apply(Candidate candidate) {
//        return new CandidateWithRecentExperienceDTO(
//                candidate.getId(),
//                candidate.getName(),
//                candidate.getDob(),
//                candidate.getPhoneNo()
////                candidate.
//        )
        return null;
    }
}
