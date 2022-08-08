package com.ironhack.team5crm.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactRepositoryTest {

  @Autowired
  ContactRepository contactRepository;

  @Autowired
  AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
    contactRepository.deleteAll();
    accountRepository.deleteAll();
  }

}
