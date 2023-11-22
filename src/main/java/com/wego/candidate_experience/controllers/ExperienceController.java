package com.wego.candidate_experience.controllers;

import java.util.List;
import java.util.Optional;

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

import com.wego.candidate_experience.models.Candidate;
import com.wego.candidate_experience.models.Experience;
import com.wego.candidate_experience.repositories.CandidateRepository;
import com.wego.candidate_experience.repositories.ExperienceRepository;

@RestController
@RequestMapping("experience")
public class ExperienceController {

    @Autowired
    ExperienceRepository _experienceRepo;

    @Autowired
    CandidateRepository _customerRepo;

    @GetMapping("")
    public ResponseEntity<List<Experience>> getAllExperiences() {
        try {
            List<Experience> experiences = _experienceRepo.findAll();

            return new ResponseEntity<>(experiences, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperience(@PathVariable int id) {
        try {
            Optional<Experience> experience = _experienceRepo.findById(id);
            if (experience.isPresent()) {
                return new ResponseEntity<>(experience.get(), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Experience> createNewExperience(@RequestBody Experience _experience) {
        try {
            Experience exp = _experienceRepo.save(_experience);
            return new ResponseEntity<>(exp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateCustomer(@PathVariable int id, @RequestBody Experience _experience) {
        try {
            Experience experience = _experienceRepo.findById(id).orElse(null);
            if (experience == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            experience.setCompany_name(_experience.getCompany_name());
            experience.setTitle(_experience.getTitle());
            experience.setStart_date(_experience.getStart_date());
            experience.setEnd_date(_experience.getEnd_date());

            return new ResponseEntity<>(_experienceRepo.save(_experience), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
