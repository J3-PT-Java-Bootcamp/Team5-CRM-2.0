package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;

public interface AccountService {

    List<Account> getAll() throws EmptyException;
    Account lookUpAccount(int id) throws EmptyException, DataNotFoundException;

}
