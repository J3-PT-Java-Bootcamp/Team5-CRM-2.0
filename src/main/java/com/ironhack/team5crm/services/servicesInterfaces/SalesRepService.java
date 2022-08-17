package com.ironhack.team5crm.services.servicesInterfaces;

import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;

public interface SalesRepService {

    //she types “Report Lead by SalesRep”.
    List <Lead> leadersBySalesRep();

    //“Report Opportunity by SalesRep”, only a count or could be print all list?
    List <Opportunity> opportunityBySalesRep();

    //“Report CLOSED-WON by SalesRep”
    List <Opportunity> closeWonBySalesRep();


    //*******  dont demmand but necessary

    SalesRep findSalesRepById(int id) throws DataNotFoundException;

    //fired
    void deleteSalesRep(int id);

    //SalesRep could be a binary person, need update

    SalesRep update(SalesRep newSalesRep);

    //adding a new sales rep

    SalesRep newSalesRep(SalesRep salesRep);

    List <SalesRep> findAllSalesRep();

    List <SalesRep> getAllSalesRep() throws EmptyException;

}
