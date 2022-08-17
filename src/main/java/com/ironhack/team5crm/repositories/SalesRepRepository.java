package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {

    //native query
    @Query(value = "SELECT * FROM leads A, sales_reps B WHERE A.sales_rep_id = B.id", nativeQuery = true)
    List <Lead> findLeadsBySalesRep();
}
