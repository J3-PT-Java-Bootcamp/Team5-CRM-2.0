package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import com.ironhack.team5crm.services.interfaceService.SalesRepService;
import com.ironhack.team5crm.ui.ConsoleOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesRepServiceImpl implements SalesRepService, ConsoleOperations {

    @Autowired
    SalesRepRepository salesRepRepository;

    public SalesRep newSalesRep(String name) {
        return salesRepRepository.save(new SalesRep(name));
    }

    public List<SalesRep> findAllSalesRep() {
        return salesRepRepository.findAll();
    }

    public SalesRep findSalesRepById(int id) throws DataNotFoundException {
        var salesRepFound = salesRepRepository.findById(id);
        if (salesRepFound.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            return salesRepFound.get();
        }
    }

    public List<SalesRep> getAllSalesRep() throws EmptyException {
        var salesReps = salesRepRepository.findAll();
        if (salesReps.isEmpty()) {
            throw new EmptyException();
        } else {
            return salesReps;
        }
    }


    //A count of Leads by SalesRep can be displayed by typing “Report Lead by SalesRep”
    public Map <Object, Object> counterLeadsBySales() throws EmptyException {
        List <Object[]> saleReps = salesRepRepository.countByLeads();
        Map<Object,Object> values = new HashMap<>();
        if(saleReps.isEmpty()){
            throw new EmptyException();
        } else {
            for(int i = 0; i < saleReps.size(); i++){
                values.put(saleReps.get(i)[1], saleReps.get(i)[2]);
            }
        }
        return values;
    }

    //A count of all Opportunities by SalesRep
    @Override
    public Map<Object, Object> counterOpportunitiesBySalesRep() throws EmptyException {
        List <Object[]> salesRep = salesRepRepository.opportunitiesBySalesRep();
        Map <Object, Object> count = new HashMap<>();
        if ((salesRep.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < salesRep.size(); i++){
                count.put(salesRep.get(i)[0], salesRep.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> counterOpportunitiesByStatus(String status) throws EmptyException {
        Map <Object, Object> count = new HashMap<>();

        switch (status) {
            case CLOSE_WON -> {   //A count of all CLOSED_WON Opportunities by SalesRep

                List<Object[]> product = salesRepRepository.opportunitiesByStatus(CLOSE_WON_TABLES);
                if ((product.isEmpty())) {
                    throw new EmptyException();
                } else {
                    for (int i = 0; i < product.size(); i++) {
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSE_LOST -> { //A count of all CLOSED_LOST Opportunities by SalesRe

                List<Object[]> product = salesRepRepository.opportunitiesByStatus(CLOSE_LOST_TABLES);
                if ((product.isEmpty())) {
                    throw new EmptyException();
                } else {
                    for (int i = 0; i < product.size(); i++) {
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case OPEN -> { //A count of all OPEN Opportunities by SalesRep

                List<Object[]> product = salesRepRepository.opportunitiesByStatus(OPEN);
                if ((product.isEmpty())) {
                    throw new EmptyException();
                } else {
                    for (int i = 0; i < product.size(); i++) {
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }
        return count;
    }

}
