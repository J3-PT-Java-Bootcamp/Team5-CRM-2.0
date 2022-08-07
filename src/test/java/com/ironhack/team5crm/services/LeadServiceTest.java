package com.ironhack.team5crm.services;

import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.LeadRepository;
import com.ironhack.team5crm.data.OpportunityRepository;
import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.data.datasources.impl.InMemoryDatasource;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.domain.Lead;
import com.ironhack.team5crm.domain.enums.Industry;
import com.ironhack.team5crm.domain.enums.Product;
import com.ironhack.team5crm.domain.enums.Status;
import com.ironhack.team5crm.domain.exceptions.Team5CrmException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadServiceTest {

    Datasource datasource = InMemoryDatasource.getInstance();

    @Autowired
    LeadRepository leadRepo;

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepo;

    @Autowired
    LeadService leadService;

    Lead lead1;
    Lead lead2;

    @BeforeEach
    void setUp() {
        leadRepo.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepo.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_newLead() {
        var lead = new Lead("test", "666666666", "test@gmail.com", "company");
        var leadCreated = leadService.newLead(lead.getName(), lead.getPhoneNumber(), lead.getEmail(),
                lead.getCompanyName());
        assertEquals(lead.getName(), leadCreated.getName());
        assertEquals(lead.getPhoneNumber(), leadCreated.getPhoneNumber());
        assertEquals(lead.getEmail(), leadCreated.getEmail());
        assertEquals(lead.getCompanyName(), leadCreated.getCompanyName());
    }

    @Test
    void test_convert() {
        addLeadsToDatasource();
        var product = Product.HYBRID;
        var prodQty = 5;
        var industry = Industry.MANUFACTURING;
        var emp = 60;
        var city = "BCN";
        var country = "Spain";

        Team5CrmException exception = null;
        try {
            var account = leadService.convert(this.lead1.getId(), product, prodQty, industry, emp, city, country);
            assertNotNull(account.getId());
            assertNotNull(account.getContactList().get(0).getId());
            var oppCreated = account.getOpportunityList().get(0);
            assertNotNull(oppCreated);
            assertNotNull(oppCreated.getId());
            assertNotNull(oppCreated.getDecisionMaker());
            assertNotNull(oppCreated.getDecisionMaker().getId());
            assertEquals(this.lead1.getName(), oppCreated.getDecisionMaker().getName());
            assertEquals(this.lead1.getPhoneNumber(), oppCreated.getDecisionMaker().getPhoneNumber());
            assertEquals(this.lead1.getEmail(), oppCreated.getDecisionMaker().getEmail());
            assertEquals(account.getContactList().get(0), oppCreated.getDecisionMaker());
            assertEquals(product, oppCreated.getProduct());
            assertEquals(prodQty, oppCreated.getQuantity());
            assertEquals(Status.OPEN, oppCreated.getStatus());
            assertEquals(industry, account.getIndustry());
            assertEquals(emp, account.getEmployeesCount());
            assertEquals(city, account.getCity());
            assertEquals(country, account.getCountry());
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);
    }

    @Test
    void test_getAllLeads() {
        addLeadsToDatasource();

        Team5CrmException exception = null;
        try {
            var leads = leadService.getAllLeads();
            assertEquals(2, leads.size());
        } catch (EmptyException e) {
            exception = e;
        }
        assertNull(exception);

    }

    @Test
    void test_getAllLeads_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> leadService.getAllLeads());
    }

    @Test
    void test_lookUpLead() {

        addLeadsToDatasource();

        Team5CrmException exception = null;
        try {
            var leadFound = leadService.lookUpLead(this.lead2.getId());
            assertEquals(this.lead2.getId(), leadFound.getId());
            assertEquals(this.lead2.getName(), leadFound.getName());
            assertEquals(this.lead2.getPhoneNumber(), leadFound.getPhoneNumber());
            assertEquals(this.lead2.getEmail(), leadFound.getEmail());
            assertEquals(this.lead2.getCompanyName(), leadFound.getCompanyName());
        } catch (EmptyException e) {
            exception = e;
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);
    }

    @Test
    void test_lookUpLead_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> leadService.lookUpLead(5));
    }

    private void addLeadsToDatasource() {
        lead1 = leadService.newLead("lead 1", "111111111", "lead1@gmail.com", "company 1");
        lead2 = leadService.newLead("lead 2", "222222222", "lead2@hotmail.com", "company inc 2");
    }
}
