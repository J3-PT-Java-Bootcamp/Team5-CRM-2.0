package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;

public interface LeadService {

    Lead newLead(String name, String phone, String email, String company, SalesRep assignedSalesRep);
    Account convert(Lead lead, Product product, int productQuantity, Account account);
    List<Lead> getAllLeads() throws EmptyException;
    Lead lookUpLead(int id) throws DataNotFoundException, EmptyException;

}
