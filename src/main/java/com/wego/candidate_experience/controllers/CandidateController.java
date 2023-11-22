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

import com.wego.candidate_experience.models.*;
import com.wego.candidate_experience.repositories.CandidateRepository;
import com.wego.candidate_experience.repositories.ExperienceRepository;

@RestController
@RequestMapping("/customer")
public class CandidateController {

    @Autowired
    CandidateRepository _customerRepo;

    @Autowired
    ExperienceRepository _experienceRepo;

    @GetMapping("")
    public ResponseEntity<List<Candidate>> getAllCustomers() {
        try {
            List<Candidate> customers = _customerRepo.findAll();

            return new ResponseEntity<>(customers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCustomer(@PathVariable int id) {
        try {
            Optional<Candidate> customer = _customerRepo.findById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/experiences")
    public ResponseEntity<List<Experience>> getExperiences(@PathVariable int id) {
        try {
            Boolean customer_exists = _customerRepo.findById(id).isPresent();
            if (!customer_exists) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Experience> experiences = _experienceRepo.findAllByCustomerId(id);

            return new ResponseEntity<>(experiences, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("")
    public ResponseEntity<Candidate> createNewCustomer(@RequestBody Candidate _customer) {
        try {
            _customerRepo.save(_customer);
            return new ResponseEntity<>(_customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCustomer(@PathVariable int id, @RequestBody Candidate _customer) {
        try {
            Candidate customer = _customerRepo.findById(id).orElse(null);
            if (customer == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            customer.setName(_customer.getName());
            customer.setPhoneNo(_customer.getPhoneNo());
            customer.setDob(_customer.getDob());

            return new ResponseEntity<>(_customerRepo.save(_customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
