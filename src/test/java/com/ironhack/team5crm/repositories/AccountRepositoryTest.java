package com.ironhack.team5crm.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.models.enums.Industry;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountRepositoryTest {

  @Autowired
  AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    accountRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    accountRepository.deleteAll();
  }

  @Test
  void test_saveAccount() {
    var acc = new Account(Industry.MANUFACTURING, 50, "BCN", "Spain", null, null);
    var accCreated = accountRepository
        .save(acc);
    var accFound = accountRepository.findById(accCreated.getId());
    assertTrue(accFound.isPresent());
    assertEquals(accFound.get().getId(), accCreated.getId());
  }

  @Test
  void test_deleteAllAccounts() {
    var acc = new Account(Industry.MANUFACTURING, 50, "BCN", "Spain", null, null);
    accountRepository
        .save(acc);
    var accounts = accountRepository.findAll();
    assertEquals(1, accounts.size());
    accountRepository.deleteAll();
    accounts = accountRepository.findAll();
    assertEquals(0, accounts.size());
  }

}
