package com.ironhack.team5crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ironhack.team5crm.models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


}
