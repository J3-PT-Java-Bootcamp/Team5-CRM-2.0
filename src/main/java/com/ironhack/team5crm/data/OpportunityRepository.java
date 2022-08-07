package com.ironhack.team5crm.data;

import com.ironhack.team5crm.domain.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
}
