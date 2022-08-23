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
    Map<Object, Object> reportsByProducts(String stats, String product) throws EmptyException;


    //BY COUNTRY

    //******************reportByCountry

    Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException;
    Map<Object, Object> reportByCountry(String stats, String country) throws EmptyException;

    //BY CITY

    //******************  reportBy CITY

    Map<Object, Object> counterOpportunitiesByCity(String country) throws EmptyException;
    Map<Object, Object> reportByCity(String stats, String country) throws EmptyException;

}
