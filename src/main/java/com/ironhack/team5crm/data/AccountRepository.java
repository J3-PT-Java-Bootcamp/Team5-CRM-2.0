package com.ironhack.team5crm.data;

import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.domain.Account;

/**
 * You should only use this repository when converting a lead and creating the
 * account with the opportunities an contacts
 */
public class AccountRepository {

    private static AccountRepository instance;
    private final Datasource datasource;

    private AccountRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static AccountRepository getInstance(Datasource datasource) {
        if (instance == null) {
            instance = new AccountRepository(datasource);
        }
        return instance;
    }

    public int getMaxAccountId() {
        return datasource.getMaxAccountId();
    }

    public Account saveAccount(Account account) {
        return datasource.saveAccount(account);
    }

    public void deleteAllAccounts() {
        datasource.deleteAllAccounts();
    }
}
