package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
}
