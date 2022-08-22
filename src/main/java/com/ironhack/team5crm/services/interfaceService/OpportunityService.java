package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;
import java.util.Map;

public interface OpportunityService {

    List<Opportunity> getAllOpportunities() throws EmptyException;
    Opportunity lookUpOpportunity(int id) throws EmptyException, DataNotFoundException;
    Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException;

    //***new report methods

    Map<Object, Object> counterOpportunitiesByProduct(String product) throws EmptyException;

    Map<Object, Object> counterOpportunitiesByCloseWon() throws EmptyException;

    Map<Object, Object> counterOpportunitiesByCloseLost() throws EmptyException;

    Map<Object, Object> counterOpportunitiesByOpen() throws EmptyException;
}
