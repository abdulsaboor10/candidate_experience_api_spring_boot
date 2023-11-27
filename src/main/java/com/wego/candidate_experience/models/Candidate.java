package com.wego.candidate_experience.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Candidate {
    
    // @JsonProperty("identifier") //will be seen as identifier in json data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate dob;
    private String phoneNo;

    @OneToMany(mappedBy = "candidate" , cascade = {CascadeType.ALL})
    private Set<Experience> experiences = new HashSet<>();

    public Candidate(String name , LocalDate dob , String phoneNo){
        this.dob = dob;
        this.name = name;
        this.phoneNo = phoneNo;
    }

}
