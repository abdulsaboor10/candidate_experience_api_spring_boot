package com.wego.candidate_experience.controllers;

import java.util.List;
import java.util.Optional;

import com.wego.candidate_experience.dto.CandidateDTO;
import com.wego.candidate_experience.dto.CandidateWithExperiencesDTO;
import com.wego.candidate_experience.dto.CandidateWithRecentExperienceDTO;
import com.wego.candidate_experience.dto.ExperienceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wego.candidate_experience.models.*;
import com.wego.candidate_experience.services.CandidateService;
import com.wego.candidate_experience.services.ExperienceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    ExperienceService experienceService;


    @GetMapping("")
    public ResponseEntity<Page<?>> getAll(
            @RequestParam(name = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(name = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(name = "offset",defaultValue = "0") int offset,
            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
            @RequestParam(name = "recentExperience",defaultValue = "false") Boolean recentExperience,
            @RequestParam(name = "experience",defaultValue = "false") Boolean experience
    ) {
        try {

            if (pageSize > 100){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Page<?> candidates;
            if (recentExperience){
                candidates = candidateService.getAllCandidatesWithRecentExperience(sortBy,offset,pageSize,desc);

            }else if (experience){
                candidates = candidateService.getAllCandidatesWithExperience(sortBy,offset,pageSize,desc);
            }
            else{
                candidates = candidateService.getAllCandidates(sortBy,offset,pageSize,desc);
            }
            return ResponseEntity.status(HttpStatus.OK).body(candidates);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/search/{query}")
    public ResponseEntity<Page<CandidateDTO>> search(
            @PathVariable String query,
            @RequestParam(name = "offset",defaultValue = "0") int offset,
            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
    ){
        try{
            Page<CandidateDTO> candidates = candidateService.searchCandidates(query,offset,pageSize);
            return new ResponseEntity<>(candidates,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CandidateDTO> get( @PathVariable int id) {
        try {


            CandidateDTO candidate = candidateService.getCandidate(id);

            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/with-recent-experience")
    public ResponseEntity<CandidateWithRecentExperienceDTO> getWithRecentExperience(@PathVariable int id){
        try{
            CandidateWithRecentExperienceDTO candidate = candidateService.getCandidateWithRecentExperience((id));
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/with-experiences")
    public ResponseEntity<CandidateWithExperiencesDTO> getWithAllExperiences(@PathVariable int id){
        try{
            CandidateWithExperiencesDTO candidate = candidateService.getCandidateWithAllExperiences((id));
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/{id}/experiences")
    public ResponseEntity<List<ExperienceDTO>> getExperiences(@PathVariable int id) {
        try {
            List<ExperienceDTO> experiences = candidateService.getExperiences(id);

            return new ResponseEntity<>(experiences, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("")
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
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            candidateService.deleteCandidate(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
