package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesRepService {

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
        if(salesRepFound.isEmpty()){
            throw new DataNotFoundException();
        }else{
            return salesRepFound.get();
        }
    }

}
