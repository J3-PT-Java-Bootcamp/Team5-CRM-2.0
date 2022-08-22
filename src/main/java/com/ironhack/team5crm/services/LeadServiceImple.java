package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.*;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.AccountRepository;
import com.ironhack.team5crm.repositories.ContactRepository;
import com.ironhack.team5crm.repositories.LeadRepository;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.services.interfaceService.LeadService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class LeadServiceImple implements LeadService {

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
    public Account convert(Lead lead, Product product, int productQuantity, Account account) {
        var contactToSave = new Contact(lead.getName(), lead.getPhoneNumber(),
                lead.getEmail(), null);
        contactToSave = contactRepository.save(contactToSave);
        var oppToSave = new Opportunity(
                Status.OPEN, product, productQuantity, contactToSave, null, lead.getSalesRep());
        oppToSave = opportunityRepository.save(oppToSave);

        account.getContactList().add(contactToSave);
        account.getOpportunityList().add(oppToSave);

        account = accountRepository.save(account);

        // TODO: this should not be needed to set ids on these entities, it should be
        // done when saving the account with the lists inside it
        contactToSave.setAccount(account);
        contactRepository.save(contactToSave);
        oppToSave.setAccount(account);
        opportunityRepository.save(oppToSave);

        leadRepository.deleteById(lead.getId());
        return account;
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
    public Lead lookUpLead(int id) throws DataNotFoundException, EmptyException {
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
