package com.ironhack.team5crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ironhack.team5crm.models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    //The mean employeeCount
    @Query(value = "SELECT FLOOR(AVG(A1.employee_count)) AS MEDIAN\n" +
                        "FROM (\n" +
                             "SELECT employee_count, ROW_NUMBER()  OVER(ORDER BY employee_count) AS rower\n" +
                                    "FROM accounts\n" +
                                ") A1,\n" +
                                    "(SELECT COUNT(*) AS counter FROM accounts) A2\n" +
                    "WHERE A1.rower IN\n" +
                            "(FLOOR((A2.counter + 1) / 2),\n" +
                            "FLOOR((A2.counter + 2) / 2));", nativeQuery = true)
    List<Object[]> medianEmployees();
}
