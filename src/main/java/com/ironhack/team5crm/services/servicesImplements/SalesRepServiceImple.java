package com.ironhack.team5crm.services.servicesImplements;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import com.ironhack.team5crm.services.servicesInterfaces.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("data")
public class SalesRepServiceImple implements SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Override
    public SalesRep newSalesRep(SalesRep newSalesRep) {
        return salesRepRepository.save(newSalesRep);
    }

    @Override
    public List<SalesRep> findAllSalesRep() {
        return salesRepRepository.findAll();
    }

    @Override
    public SalesRep findSalesRepById(int id) throws DataNotFoundException {
        var salesRepFound = salesRepRepository.findById(id);
        if (salesRepFound.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            return salesRepFound.get();
        }
    }

    @Override
    public List<SalesRep> getAllSalesRep() throws EmptyException {
        var salesReps = salesRepRepository.findAll();
        if (salesReps.isEmpty()) {
            throw new EmptyException();
        } else {
            return salesReps;
        }
    }

    //************************************* new methods



    @Override
    public List<Object[]> leadersBySalesRep() {

        return salesRepRepository.findLeadsBySalesRep();
    }

    @Override
    public List<Opportunity> opportunityBySalesRep() {
        return null;
    }

    @Override
    public List<Opportunity> closeWonBySalesRep() {
        return null;
    }

    public void deleteSalesRep(int id) {
        salesRepRepository.deleteById(id);
    }

    @Override
    public SalesRep update(SalesRep newSalesRep) {
        return null;
    }


}
