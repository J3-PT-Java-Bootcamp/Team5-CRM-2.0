package com.ironhack.team5crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ironhack.team5crm.models.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
}
