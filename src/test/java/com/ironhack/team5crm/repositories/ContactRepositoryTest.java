package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.datasources.impl.InMemoryDatasource;
import com.ironhack.team5crm.domain.Account;
import com.ironhack.team5crm.domain.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ContactRepositoryTest {

  ContactRepository contactRepository;
  AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    var datasource = InMemoryDatasource.getInstance();
    contactRepository = ContactRepository.getInstance(datasource);
    accountRepository = AccountRepository.getInstance(datasource);
  }

  @AfterEach
  void tearDown() {
    accountRepository.deleteAllAccounts();
  }

  @Test
  void test_getInstance() {
    var datasource = InMemoryDatasource.getInstance();
    assertEquals(contactRepository, ContactRepository.getInstance(datasource));
  }

  @Test
  void test_getMaxContactId() {
    var initId = contactRepository.getMaxContactId();
    var contactList = new ArrayList<Contact>();
    contactList.add(new Contact(contactRepository.getMaxContactId(), "test", "5555555", "email@gmail.com"));
    var accCreated = accountRepository
        .saveAccount(new Account(accountRepository.getMaxAccountId(), null, 0, null, null, contactList, null));
    var afterId = contactRepository.getMaxContactId();

    assertEquals(initId, accCreated.getId());
    assertNotEquals(initId, afterId);
    assertEquals(initId + 1, afterId);

  }

}
