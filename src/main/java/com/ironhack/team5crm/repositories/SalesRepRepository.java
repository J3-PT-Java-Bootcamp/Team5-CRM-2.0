package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {

    @Query(value = "SELECT  A.id, A.name,COUNT(*)\n" +
            "    FROM sales_reps A\n" +
            "    JOIN leads l on A.id = l.sales_rep_id\n" +
            "    GROUP BY A.id, A.name;", nativeQuery = true)
    List <Object[]> countByLeads();

    @Query(value = "SELECT B.name,COUNT(*)\n" +
            "    FROM opportunities A\n" +
            "    JOIN sales_reps B on B.id = A.sales_rep_id\n" +
            "    GROUP BY B.name", nativeQuery = true)
    List <Object[]> opportunitiesBySalesRep();


    @Query(value = "SELECT B.name,COUNT(*)\n" +
            "    FROM opportunities A\n" +
            "    JOIN sales_reps B on A.sales_rep_id = B.id\n" +
            "    WHERE A.status LIKE 'close_won'\n" +
            "    GROUP BY B.name;", nativeQuery = true)
    List <Object[]> opportunitiesByCloseWon();

    @Query(value = "SELECT B.name,COUNT(*)\n" +
            "    FROM opportunities A\n" +
            "    JOIN sales_reps B on A.sales_rep_id = B.id\n" +
            "    WHERE A.status LIKE 'close_lost'\n" +
            "    GROUP BY B.name;", nativeQuery = true)
    List <Object[]> opportunitiesByCloseLost();

    @Query(value = "SELECT B.name,COUNT(*)\n" +
            "    FROM opportunities A\n" +
            "    JOIN sales_reps B on A.sales_rep_id = B.id\n" +
            "    WHERE A.status LIKE 'open'\n" +
            "    GROUP BY B.name;", nativeQuery = true)
    List <Object[]> opportunitiesByOpen();
}
