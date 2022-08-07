package com.ironhack.team5crm.services;

import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.LeadRepository;
import com.ironhack.team5crm.data.OpportunityRepository;
import com.ironhack.team5crm.data.exceptions.DataNotFoundException;
import com.ironhack.team5crm.domain.Account;
import com.ironhack.team5crm.domain.Contact;
import com.ironhack.team5crm.domain.Lead;
import com.ironhack.team5crm.domain.Opportunity;
import com.ironhack.team5crm.domain.enums.Industry;
import com.ironhack.team5crm.domain.enums.Product;
import com.ironhack.team5crm.domain.enums.Status;
import com.ironhack.team5crm.services.exceptions.EmptyException;

import java.util.List;

public class LeadService {

    private static LeadService instance;
    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;
    private final OpportunityRepository opportunityRepository;

    // * CONSTRUCTORS
    // **********************************************
    private LeadService(LeadRepository leadRepository, ContactRepository contactRepository,
            AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        this.leadRepository = leadRepository;
        this.contactRepository = contactRepository;
        this.accountRepository = accountRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public static LeadService getInstance(LeadRepository leadRepository, ContactRepository contactRepository,
            AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        if (instance == null) {
            instance = new LeadService(leadRepository, contactRepository, accountRepository, opportunityRepository);
        }
        return instance;
    }

    public Lead newLead(String name, String phone, String email, String company) {
        var maxId = leadRepository.maxLeadId();
        Lead newLead = new Lead(maxId, name, phone, email, company);

        return leadRepository.saveLead(newLead);
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
        int maxIdAccount = accountRepository.getMaxAccountId();
        var contactToSave = new Contact(contactRepository.getMaxContactId(), lead.getName(), lead.getPhoneNumber(),
                lead.getEmail());
        var contactList = List.of(contactToSave);
        var opportunityList = List.of(new Opportunity(opportunityRepository.getMaxOpportunityId(), contactToSave,
                Status.OPEN, product, productQuantity));
        var accountToSave = new Account(maxIdAccount, industry, employees, city, country, contactList, opportunityList);
        var accountCreated = accountRepository.saveAccount(accountToSave);
        leadRepository.deleteLead(leadId);
        return accountCreated;
    }

    /**
     * Method that shows all available Leads in the database
     */
    public List<Lead> getAllLeads() throws EmptyException {
        var leads = leadRepository.getAllLeads();
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
        if (leadRepository.getAllLeads().size() != 0) {
            return leadRepository.findById(id);
        } else {
            throw new EmptyException();
        }
    }

}
