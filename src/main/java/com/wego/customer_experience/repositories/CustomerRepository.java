package com.wego.customer_experience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wego.customer_experience.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    
}
