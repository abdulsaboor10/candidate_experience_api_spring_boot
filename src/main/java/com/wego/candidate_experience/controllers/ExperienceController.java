package com.wego.candidate_experience.controllers;

import java.util.List;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.ExperienceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.models.Experience;
import com.wego.candidate_experience.services.CandidateService;
import com.wego.candidate_experience.services.ExperienceService;

@RestController
@RequestMapping("experience")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @Autowired
    CandidateService candidateService;

    @GetMapping("")
    public ResponseEntity<List<?>> getAll(
            @RequestParam(name = "candidate" , defaultValue = "false") Boolean candidate
    ) {
        try {
            List<?> experiences;
            if (candidate){
                experiences = experienceService.getAllExperiencesWithCandidate();
            }else{
                experiences = experienceService.getAllExperiences();
            }

            return new ResponseEntity<>(experiences, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> get(@PathVariable int id) {
        try {
            ExperienceDTO experience = experienceService.getExperience(id);
            if (experience != null) {
                return new ResponseEntity<>(experience , HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{candidateId}")
    public ResponseEntity<Experience> post(@PathVariable int candidateId , @RequestBody Experience _experience) {
        try {

            CandidateDTO candidate = candidateService.getCandidate(candidateId);
            if (candidate == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Experience experience = experienceService.createExperience(candidateId, _experience);
            return new ResponseEntity<>(experience, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> put(@PathVariable int id, @RequestBody Experience _experience) {
        try {
            Experience experience = experienceService.updateExperience(id, _experience);

            return new ResponseEntity<>(experience, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
