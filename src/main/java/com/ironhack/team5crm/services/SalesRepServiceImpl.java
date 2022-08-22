package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import com.ironhack.team5crm.services.interfaceService.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesRepServiceImpl implements SalesRepService {

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


}
