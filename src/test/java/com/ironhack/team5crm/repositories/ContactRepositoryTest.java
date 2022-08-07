package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.datasources.impl.InMemoryDatasource;
import com.ironhack.team5crm.domain.Account;
import com.ironhack.team5crm.domain.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
