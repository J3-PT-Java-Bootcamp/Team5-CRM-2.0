package com.ironhack.team5crm.services.interfaceService;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;
import java.util.Map;

public interface SalesRepService {
    SalesRep newSalesRep(String name);

    List<SalesRep> findAllSalesRep();

    SalesRep findSalesRepById(int id) throws DataNotFoundException;

    List<SalesRep> getAllSalesRep() throws EmptyException;

    Map<Object, Object> counterLeadsBySales() throws EmptyException;

}
