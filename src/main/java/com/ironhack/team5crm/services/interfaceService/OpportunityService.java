package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;
import java.util.Map;

public interface OpportunityService {

    List<Opportunity> getAll() throws EmptyException;
    Opportunity lookUpOpportunity(int id) throws EmptyException, DataNotFoundException;
    Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException;

    // new report methods:
    // CUSTOM QUERIES FOR DASHBOARDS


    // **********************************************
    // *  BY PRODUCT
    // **********************************************
    Map<Object, Object> counterOpportunitiesByProduct(String product) throws EmptyException;
    Map<Object, Object> reportsByProducts(String stats, String product) throws EmptyException;


    // **********************************************
    // *  BY COUNTRY
    // **********************************************
    Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException;
    Map<Object, Object> reportByCountry(String stats, String country) throws EmptyException;


    // **********************************************
    // *  BY CITY
    // **********************************************
    Map<Object, Object> counterOpportunitiesByCity(String country) throws EmptyException;
    Map<Object, Object> reportByCity(String stats, String country) throws EmptyException;


    // **********************************************
    // *  BY INDUSTRY
    // **********************************************
    Map<Object, Object> counterOpportunitiesByIndustry(String industry) throws EmptyException;
    Map<Object, Object> reportByIndustry(String stats, String industry) throws EmptyException;
}
