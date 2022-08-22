package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {

    @Query(value = "SELECT B.id, B.name, A.name 'Lead Name', A.company_name, A.email, A.phone_number FROM leads A, sales_reps B WHERE A.sales_rep_id = B.id", nativeQuery = true)
    List<Object[]> findLeadsBySalesRep();

    @Query(value = "SELECT  A.id, A.name,COUNT(*)\n" +
            "    FROM sales_reps A\n" +
            "    JOIN leads l on A.id = l.sales_rep_id\n" +
            "    GROUP BY A.id, A.name;", nativeQuery = true)
    List <Object[]> countByLeads();

    @Query(value = "\n" +
            "SELECT B.name, COUNT(A.id)\n" +
            "    FROM sales_reps B, leads A\n" +
            "    WHERE A.sales_rep_id = B.id\n" +
            "    GROUP BY B.name;", nativeQuery = true)
    List <SalesRep> response();
}
