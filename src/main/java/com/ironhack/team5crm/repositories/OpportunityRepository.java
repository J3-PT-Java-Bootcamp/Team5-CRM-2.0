package com.ironhack.team5crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ironhack.team5crm.models.Opportunity;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    // **********************************************
    // *  BY PRODUCT
    // **********************************************
    /**
    * A count of all Opportunities by the product
    */
    @Query(value = "SELECT product, COUNT(*)\n" +
                    "FROM opportunities\n" +
                    "WHERE product LIKE :product\n" +
                    "GROUP BY product;",nativeQuery = true)
    List<Object[]> countByProduct(@Param("product") String product);


    /******************************************************************
    //A count of all CLOSED_WON Opportunities by the product
    //A count of all CLOSED_LOST Opportunities by the product
    //A count of all OPEN Opportunities by the product
    */
    @Query(value = "SELECT product, COUNT(*)\n" +
                    "FROM opportunities\n" +
                    "WHERE status LIKE :status AND product LIKE :product\n" +
                    "GROUP BY product;", nativeQuery = true)
    List<Object[]> countByStatusAndProduct(@Param("status") String status, @Param("product") String product);


    // **********************************************
    // *  BY COUNTRY
    // **********************************************

    /**
    * * A count of all Opportunities by country
    */
    @Query(value = "SELECT B.country, COUNT(*)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE country LIKE :country\n" +
                    "GROUP BY B.country;",nativeQuery = true)
    List<Object[]> countByCountry(@Param("country") String country);

    /**
     * A count of all CLOSED_LOST Opportunities by country
     * A count of all OPEN Opportunities by country
     * A count of all CLOSED_WON Opportunities by country
     */

    @Query(value = "SELECT B.country, COUNT(A.status)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE A.status LIKE :status AND B.country LIKE :country\n" +
                    "GROUP BY B.country, A.status;",nativeQuery = true)
    List<Object[]> countByCountryByStatus(@Param("status") String status, @Param("country") String country);


    // **********************************************
    // *  BY CITY
    // **********************************************

    /**
     * A count of all Opportunities by the city
     */
    @Query(value = "SELECT B.city, COUNT(*)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE B.city LIKE :city\n" +
                    "GROUP BY B.city;", nativeQuery = true)
    List<Object[]> countByCity(@Param("city") String city);

    /**
     * A count of all CLOSED_WON Opportunities by the city
     */
    @Query(value = "SELECT B.city, COUNT(*)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE A.status LIKE :status AND  B.city LIKE :city\n" +
                    "GROUP BY B.city;", nativeQuery = true)
    List<Object[]> countByStatusAndCity(@Param("status") String status, @Param("city") String city);


    // **********************************************
    // *  BY INDUSTRY
    // **********************************************

    /**
     * A count of all Opportunities by the INDUSTRY
     */
    @Query(value = "SELECT B.industry, COUNT(*)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE B.industry LIKE :industry\n" +
                    "GROUP BY B.industry;", nativeQuery = true)
    List<Object[]> countByIndustry(@Param("industry") String industry);

    /**
     * A count of all STATUS  Opportunities by the INDUSTRY
     */
    @Query(value = "SELECT B.industry, COUNT(*)\n" +
                    "FROM opportunities A\n" +
                    "JOIN accounts B on A.account_id = B.id\n" +
                    "WHERE A.status LIKE :status AND  B.industry LIKE :industry\n" +
                    "GROUP BY B.industry;", nativeQuery = true)
    List<Object[]> countByIndustryStatus(@Param("status") String status, @Param("industry") String industry);

}
