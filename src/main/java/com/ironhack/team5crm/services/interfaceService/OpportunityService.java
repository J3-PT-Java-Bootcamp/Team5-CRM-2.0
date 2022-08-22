package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;

public interface OpportunityService {

    List<Opportunity> getAllOpportunities() throws EmptyException;
    Opportunity lookUpOpportunity(int id) throws EmptyException, DataNotFoundException;
    Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException;

}