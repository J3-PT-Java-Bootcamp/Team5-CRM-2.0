package com.ironhack.team5crm.services;

import java.util.List;

import com.ironhack.team5crm.services.interfaceService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.repositories.AccountRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

@Service
public class AccountServiceImple implements AccountService {

  @Autowired
  AccountRepository accountRepository;

  public List<Account> getAllAccounts() throws EmptyException {
    var accounts = accountRepository.findAll();
    if (accounts.isEmpty()) {
      throw new EmptyException();
    } else {
      return accounts;
    }
  }

  public Account lookUpAccount(int id) throws EmptyException, DataNotFoundException {
    if (accountRepository.findAll().size() != 0) {
      var account = accountRepository.findById(id);
      if (account.isPresent()) {
        return account.get();
      } else {
        throw new DataNotFoundException();
      }
    } else {
      throw new EmptyException();
    }
  }

}
