package com.ironhack.team5crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ironhack.team5crm.models.Opportunity;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    //A count of all Opportunities by the product
    @Query(value = "SELECT product, COUNT(*)\n" +
            "    FROM opportunities\n" +
            "    WHERE product LIKE :product\n" +
            "    GROUP BY product;",nativeQuery = true)
    List<Object[]> countByProduct(@Param("product") String product);

    //A count of all CLOSED_WON Opportunities by the product
    @Query(value = "SELECT product, COUNT(*)\n" +
            "    FROM opportunities\n" +
            "    WHERE status LIKE 'close_won'\n" +
            "    GROUP BY product;", nativeQuery = true)
    List<Object[]> countByCloseWon();

    //A count of all CLOSED_LOST Opportunities by the product
    @Query(value = "SELECT product, COUNT(*)\n" +
            "    FROM opportunities\n" +
            "    WHERE status LIKE 'close_lost'\n" +
            "    GROUP BY product;", nativeQuery = true)
    List<Object[]> countByCloseLost();

    //A count of all OPEN Opportunities by the product
    @Query(value = "SELECT product, COUNT(*)\n" +
            "    FROM opportunities\n" +
            "    WHERE status LIKE 'open'\n" +
            "    GROUP BY product;", nativeQuery = true)
    List<Object[]> countByOpen();


    //************************  BY COUNTRY

    //A count of all Opportunities by country
    @Query(value = "SELECT B.country, COUNT(*)\n" +
            "    FROM opportunities A\n" +
            "    JOIN accounts B on A.account_id = B.id\n" +
            "    WHERE country LIKE :country\n" +
            "    GROUP BY B.country;",nativeQuery = true)
    List<Object[]> countByCountry(@Param("country") String country);

    //A count of all CLOSED_WON Opportunities by country

}
