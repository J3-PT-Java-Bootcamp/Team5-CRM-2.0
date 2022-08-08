package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.*;
import com.ironhack.team5crm.models.enums.Industry;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.AccountRepository;
import com.ironhack.team5crm.repositories.ContactRepository;
import com.ironhack.team5crm.repositories.LeadRepository;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    public Lead newLead(String name, String phone, String email, String company, SalesRep assignedSalesRep) {
        Lead newLead = new Lead(name, phone, email, company, assignedSalesRep);

        return leadRepository.save(newLead);
    }

    /**
     * Method that converts a lead into an opportunity, a contact and both into an
     * account
     *
     * @param leadId
     */
    public Account convert(int leadId, Product product, int productQuantity, Industry industry, int employees,
            String city, String country) throws DataNotFoundException {
        var lead = leadRepository.findById(leadId);
        if (lead.isPresent()) {
            var leadFound = lead.get();
            var contactToSave = new Contact(leadFound.getName(), leadFound.getPhoneNumber(),
                    leadFound.getEmail(), null);
            contactToSave = contactRepository.save(contactToSave);
            var contactList = List.of(contactToSave);
            var oppToSave = new Opportunity(
                    Status.OPEN, product, productQuantity, contactToSave, null, leadFound.getSalesRep());
            oppToSave = opportunityRepository.save(oppToSave);
            var opportunityList = List.of(oppToSave);
            var accountToSave = new Account(industry, employees, city, country, contactList, opportunityList);
            var accountCreated = accountRepository.save(accountToSave);

            // TODO: this should not be needed to set ids on these entities, it should be
            // done when saving the account with the lists inside it
            contactToSave.setAccount(accountCreated);
            oppToSave.setAccount(accountCreated);
            accountCreated = accountRepository.save(accountCreated);
            leadRepository.deleteById(leadId);
            return accountCreated;
        } else {
            throw new DataNotFoundException();
        }
    }

    /**
     * Method that shows all available Leads in the database
     */
    public List<Lead> getAllLeads() throws EmptyException {
        var leads = leadRepository.findAll();
        if (leads.size() != 0) {
            return leads;
        } else {
            throw new EmptyException();
        }
    }

    /**
     * Method that shows the requested Lead by ID
     */
    public Lead lookUpLead(int id) throws EmptyException, DataNotFoundException {
        if (leadRepository.findAll().size() != 0) {
            var lead = leadRepository.findById(id);
            if (lead.isPresent()) {
                return lead.get();
            } else {
                throw new DataNotFoundException();
            }
        } else {
            throw new EmptyException();
        }
    }

}
