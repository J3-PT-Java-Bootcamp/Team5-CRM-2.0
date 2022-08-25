package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;
import java.util.Map;

public interface SalesRepService {
    SalesRep newSalesRep(String name);

    List<SalesRep> getAll();

    SalesRep findSalesRepById(int id) throws DataNotFoundException;

    List<SalesRep> getAllSalesRep() throws EmptyException;

    //CUSTOM QUERYS FOR DASHBOARDS

    Map<Object, Object> counterLeadsBySales() throws EmptyException;

    Map <Object, Object> counterOpportunitiesBySalesRep() throws EmptyException;

    Map <Object, Object> counterOpportunitiesByStatus(String status) throws EmptyException;

}
