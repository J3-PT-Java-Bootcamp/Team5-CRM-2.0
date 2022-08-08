package com.ironhack.team5crm.repositories;

import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LeadRepositoryTest {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    SalesRep salesRep;
    Lead lead1;
    Lead lead2;
    Lead lead1Saved;
    Lead lead2Saved;

    @BeforeEach
    void setUp() {
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    void test_findById() {
        addLeadsToDatasource();
        var leadToBeFound = leadRepository.findById(lead1.getId());
        assertTrue(leadToBeFound.isPresent());
        var leadFound = leadToBeFound.get();
        assertEquals(lead1.getId(), leadFound.getId());
        assertEquals(lead1.getName(), leadFound.getName());
        assertEquals(lead1.getEmail(), leadFound.getEmail());
        assertEquals(lead1.getPhoneNumber(), leadFound.getPhoneNumber());
        assertEquals(lead1.getCompanyName(), leadFound.getCompanyName());
    }

    @Test
    void test_saveLead() {
        var lead = new Lead("lead 1", "111111111", "lead1@gmail.com", "company 1", salesRep);
        var leadSaved = leadRepository.save(lead);
        assertEquals(lead.getId(), leadSaved.getId());
        assertEquals(lead.getName(), leadSaved.getName());
        assertEquals(lead.getEmail(), leadSaved.getEmail());
        assertEquals(lead.getPhoneNumber(), leadSaved.getPhoneNumber());
        assertEquals(lead.getCompanyName(), leadSaved.getCompanyName());

        var leadToBeFound = leadRepository.findById(lead.getId());
        assertTrue(leadToBeFound.isPresent());
        var leadFound = leadToBeFound.get();
        assertEquals(leadSaved.getId(), leadFound.getId());
        assertEquals(leadSaved.getName(), leadFound.getName());
        assertEquals(leadSaved.getEmail(), leadFound.getEmail());
        assertEquals(leadSaved.getPhoneNumber(), leadFound.getPhoneNumber());
        assertEquals(leadSaved.getCompanyName(), leadFound.getCompanyName());
    }

    @Test
    void test_getAllLeads() {
        addLeadsToDatasource();
        var leads = leadRepository.findAll();
        assertEquals(2, leads.size());
    }

    @Test
    void test_deleteAllLeads() {
        addLeadsToDatasource();
        leadRepository.deleteAll();
        var leads = leadRepository.findAll();
        assertEquals(0, leads.size());
    }

    @Test
    void test_deleteLead() {
        addLeadsToDatasource();
        var leads = leadRepository.findAll();
        assertEquals(2, leads.size());
        leadRepository.deleteById(lead1.getId());
        leads = leadRepository.findAll();
        assertEquals(1, leads.size());
        assertEquals(lead2.getId(), leads.get(0).getId());
    }

    private void addLeadsToDatasource() {
        salesRep = new SalesRep("John Doe");
        salesRepRepository.save(salesRep);
                lead1 = new Lead("lead 1", "111111111", "lead1@gmail.com", "company 1", salesRep);
        lead1Saved = leadRepository.save(lead1);
        lead2 = new Lead("lead 2", "222222222", "lead2@hotmail.com", "company inc 2", salesRep);
        lead2Saved = leadRepository.save(lead2);
    }

}
