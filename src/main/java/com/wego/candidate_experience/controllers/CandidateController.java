package com.wego.candidate_experience.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wego.candidate_experience.models.*;
import com.wego.candidate_experience.services.CandidateService;
import com.wego.candidate_experience.services.ExperienceService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    ExperienceService experienceService;

    @GetMapping("")
    public ResponseEntity<List<Candidate>> getAll() {
        try {
            List<Candidate> candidates = candidateService.getAllCandidates();

            return new ResponseEntity<>(candidates, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> get(@PathVariable int id) {
        try {
            Candidate candidate = candidateService.getCandidate(id);
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/experiences")
    public ResponseEntity<List<Experience>> getExperiences(@PathVariable int id) {
        try {
            List<Experience> experiences = candidateService.getExperiences(id);

            return new ResponseEntity<>(experiences, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<Candidate> post(@RequestBody Candidate _candidate) {
        try {
            candidateService.createCandidate(_candidate);
            return new ResponseEntity<>(_candidate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> put(@PathVariable int id, @RequestBody Candidate _candidate) {
        try {
            Candidate candidate = candidateService.updateCandidate(id, _candidate);
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
