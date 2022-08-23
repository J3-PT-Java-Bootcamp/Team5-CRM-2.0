package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.services.interfaceService.OpportunityService;
import com.ironhack.team5crm.ui.ConsoleOperations;
import com.ironhack.team5crm.views.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpportunityServiceImple implements OpportunityService, ConsoleOperations {

    @Autowired
    private OpportunityRepository opportunityRepository;

    // * METHODS
    // **********************************************

    /**
     * shows all Opportunities stored in the database or gives information if there
     * are no Opportunities in the Database
     */
    public List<Opportunity> getAllOpportunities() throws EmptyException {
        var opportunities = opportunityRepository.findAll();
        if (opportunities.isEmpty()) {
            throw new EmptyException();
        } else {
            return opportunities;
        }
    }

    /**
     * shows requested opportunity by ID or gives information if the Opportunity
     * doesn't exist in the Database
     */
    public Opportunity lookUpOpportunity(int id) throws EmptyException, DataNotFoundException {
        if (opportunityRepository.findAll().size() == 0) {
            throw new EmptyException();
        } else {
            var oppToBeFound = opportunityRepository.findById(id);
            if (oppToBeFound.isEmpty()) {
                throw new DataNotFoundException();
            } else {
                return oppToBeFound.get();
            }
        }
    }

    // * METHODS TO CHANGE THE OPPORTUNITIES STATUS
    // **********************************************

    public Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException {
        var oppToBeFound = opportunityRepository.findById(id);
        if (oppToBeFound.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            var opp = oppToBeFound.get();
            opp.setStatus(status);
            return opportunityRepository.save(opp);
        }
    }

    //A count of all Opportunities by the product
    @Override
    public Map<Object, Object> counterOpportunitiesByProduct(String product) throws EmptyException {
        List <Object[]> produc = opportunityRepository.countByProduct(product);
        Map <Object, Object> count = new HashMap<>();
        if ((produc.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < produc.size(); i++){
                count.put(produc.get(i)[0], produc.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> counterOpportunitiesByCloseWon(String products) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCloseWon(products);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> counterOpportunitiesByCloseLost(String products) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCloseLost(products);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> counterOpportunitiesByOpen(String products) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByOpen(products);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    //***********************************************************************
    //A count of all Opportunities by country
    @Override
    public Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountry(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> reportByCountry(String stats, String countrys) throws EmptyException {

        Map <Object, Object> count = new HashMap<>();

        switch (stats){
            case CLOSE_WON -> {
                List <Object[]> product = opportunityRepository.countByCountryCloseWon(countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSE_LOST -> {
                List <Object[]> product = opportunityRepository.countByCountryCloseLost(countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case OPEN -> {
                List <Object[]> product = opportunityRepository.countByCountryOpen(countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }

        return count;
    }

    //***********************************************************************
    /*/ BY COUNTRY

    //A count of all Opportunities by country
    @Override
    public Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountry(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    //A count of all CLOSED_WON Opportunities by country
    @Override
    public Map<Object, Object> counterCountryByCloseWon(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountryCloseWon(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    //A count of all CLOSED_LOST Opportunities by country

    @Override
    public Map<Object, Object> counterCountryByCloseLost(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountryCloseLost(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    //A count of all OPEN Opportunities by country
    @Override
    public Map<Object, Object> counterCountryByOpen(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountryOpen(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }*/

}
