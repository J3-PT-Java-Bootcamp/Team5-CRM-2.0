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
                        "FROM opportunities\n" +
                        "WHERE product LIKE :product\n" +
                        "GROUP BY product;",nativeQuery = true)
    List<Object[]> countByProduct(@Param("product") String product);


    //******************************************************************
    //A count of all CLOSED_WON Opportunities by the product
    //A count of all CLOSED_LOST Opportunities by the product
    //A count of all OPEN Opportunities by the product

    @Query(value = "SELECT product, COUNT(*)\n" +
                        "FROM opportunities\n" +
                        "WHERE status LIKE :status AND product LIKE :product\n" +
                        "GROUP BY product;", nativeQuery = true)
    List<Object[]> countByStatusAndProduct(@Param("status") String status, @Param("product") String product);

    //************************  BY COUNTRY

    //A count of all Opportunities by country

    @Query(value = "SELECT B.country, COUNT(*)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE country LIKE :country\n" +
                        "GROUP BY B.country;",nativeQuery = true)
    List<Object[]> countByCountry(@Param("country") String country);

    //A count of all CLOSED_LOST Opportunities by country
    ////A count of all OPEN Opportunities by country
    //A count of all CLOSED_WON Opportunities by country
    @Query(value = "SELECT B.country, COUNT(A.status)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE A.status LIKE :status AND B.country LIKE :country\n" +
                        "GROUP BY B.country, A.status;",nativeQuery = true)
    List<Object[]> countByCountryByStatus(@Param("status") String status, @Param("country") String country);

    //******************* BY CITY *************************

   //A count of all Opportunities by the city
    @Query(value = "SELECT B.city, COUNT(*)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE B.city LIKE :city\n" +
                        "GROUP BY B.city;", nativeQuery = true)
    List<Object[]> countByCity(@Param("city") String city);

    //A count of all CLOSED_WON Opportunities by the city
    @Query(value = "SELECT B.city, COUNT(*)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE A.status LIKE :status AND  B.city LIKE :city\n" +
                        "GROUP BY B.city;", nativeQuery = true)
    List<Object[]> countByStatusAndCity(@Param("status") String status, @Param("city") String city);

    //******************* BY INDUSTRY *************************

    //A count of all Opportunities by the INDUSTRY

    @Query(value = "SELECT B.industry, COUNT(*)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE B.industry LIKE :industry\n" +
                        "GROUP BY B.industry;", nativeQuery = true)
    List<Object[]> countByIndustry(@Param("industry") String industry);

    // -- A count of all STATUS  Opportunities by the INDUSTRY
    @Query(value = "SELECT B.industry, COUNT(*)\n" +
                        "FROM opportunities A\n" +
                        "JOIN accounts B on A.account_id = B.id\n" +
                        "WHERE A.status LIKE :status AND  B.industry LIKE :industry\n" +
                        "GROUP BY B.industry;", nativeQuery = true)
    List<Object[]> countByIndustryStatus(@Param("status") String status, @Param("industry") String industry);


    // mean, avg, max and min

    @Query(value = "SELECT FLOOR(AVG(B.employee_count))\n" +
                            "FROM opportunities A\n" +
                            "JOIN accounts B on B.id = A.account_id", nativeQuery = true)
    int meanByEmployeeCount();

    //MEDIAN BY COUNTER EMPLOYEES IN OPPORTUNITY
    @Query(value = "SELECT FLOOR(AVG(A1.employee_count)) AS MEDIAN\n" +
                        "FROM (\n" +
                            "SELECT B.employee_count, ROW_NUMBER()  OVER(ORDER BY employee_count) AS rower\n" +
                                "FROM opportunities A\n" +
                                "JOIN accounts B on B.id = A.account_id\n" +
                        ") A1,\n" +
                        "(SELECT COUNT(*) AS counter FROM accounts) A2\n" +
                    "WHERE A1.rower IN\n" +
                        "(FLOOR((A2.counter + 1) / 2),\n" +
                        "FLOOR((A2.counter + 2) / 2));", nativeQuery = true)
    int medianByEmployeeCount();

    @Query(value = "SELECT MIN(B.employee_count)\n" +
            "FROM opportunities A\n" +
            "JOIN accounts B on B.id = A.account_id", nativeQuery = true)
    int minByEmployeeCount();

    @Query(value = "SELECT MAX(B.employee_count)\n" +
            "FROM opportunities A\n" +
            "JOIN accounts B on B.id = A.account_id", nativeQuery = true)
    int maxByEmployeeCount();

    // BY QUANTITY

    @Query(value = "SELECT FLOOR(AVG(quantity))\n" +
                        "FROM opportunities;\n", nativeQuery = true)
    int meanByQuantityCount();

    @Query(value = "SELECT FLOOR(AVG(A1.quantity))\n" +
                        "FROM (\n" +
                            "SELECT A.quantity, ROW_NUMBER()  OVER(ORDER BY quantity) AS rower\n" +
                            "FROM opportunities A\n" +
                           ") A1,\n" +
                           "(SELECT COUNT(*) AS counter FROM opportunities) A2\n" +
                        "WHERE A1.rower IN\n" +
                        "(FLOOR((A2.counter + 1) / 2),\n" +
                        "FLOOR((A2.counter + 2) / 2));", nativeQuery = true)
    int medianByQuantityCount();

    @Query(value = "SELECT MAX(quantity)\n" +
                        "FROM opportunities;", nativeQuery = true)
    int maxByQuantityCount();

    @Query(value = "SELECT MIN(quantity)\n" +
                        "FROM opportunities;", nativeQuery = true)
    int minByQuantityCount();


    // BY ACCOUNT

    @Query(value = "SELECT FLOOR(AVG(account_id))\n" +
                        "FROM opportunities;\n", nativeQuery = true)
    int meanByAccountCount();

    @Query(value = "SELECT FLOOR(AVG(A1.account_id)) AS MEDIAN\n" +
                        "FROM (\n" +
                                "SELECT A.account_id, ROW_NUMBER()  OVER(ORDER BY account_id) AS rower\n" +
                                "FROM opportunities A\n" +
                            ") A1,\n" +
                            "(SELECT COUNT(*) AS counter FROM opportunities) A2\n" +
                        "WHERE A1.rower IN\n" +
                        "(FLOOR((A2.counter + 1) / 2),\n" +
                        "FLOOR((A2.counter + 2) / 2));", nativeQuery = true)
    int medianByAccountCount();


    @Query(value = "SELECT MAX(account_id)\n" +
                        "FROM opportunities;", nativeQuery = true)
    int maxByAccountCount();

    @Query(value = "SELECT MIN(account_id)\n" +
                        "FROM opportunities;", nativeQuery = true)
    int minByAccountCount();

}
