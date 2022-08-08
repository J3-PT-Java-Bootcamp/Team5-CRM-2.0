package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityService {

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
    public Opportunity lookUpOpportunity(int id) throws DataNotFoundException, EmptyException {
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

}
