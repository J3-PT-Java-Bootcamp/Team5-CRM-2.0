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
    //CUSTOM QUERYS FOR DASHBOARDS

    //BY PRODUCT

    Map<Object, Object> counterOpportunitiesByProduct(String product) throws EmptyException;

    Map<Object, Object> counterOpportunitiesByCloseWon(String product) throws EmptyException;

    Map<Object, Object> counterOpportunitiesByCloseLost(String product) throws EmptyException;

    Map<Object, Object> counterOpportunitiesByOpen(String product) throws EmptyException;

    //BY COUNTRY

    /*
    Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException;

    Map<Object, Object> counterCountryByCloseWon(String country) throws EmptyException;

    Map<Object, Object> counterCountryByCloseLost(String country) throws EmptyException;

    Map<Object, Object> counterCountryByOpen(String country) throws EmptyException;*/

    //******************


    //******************reportByCountry

    Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException;
    Map<Object, Object> reportByCountry(String stats, String country) throws EmptyException;

}
