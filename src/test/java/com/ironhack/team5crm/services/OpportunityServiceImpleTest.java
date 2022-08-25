package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.models.exceptions.Team5CrmException;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityServiceImpleTest {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    OpportunityServiceImple opportunityServiceImple;

    Opportunity opp1;
    Opportunity opp2;

    @BeforeEach
    void setUp() {
        opportunityRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    void test_getAllOpportunities() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var opportunities = opportunityServiceImple.getAll();
            assertEquals(2, opportunities.size());
        } catch (EmptyException e) {
            exception = e;
        }
        assertNull(exception);

    }

    @Test
    void test_getAllOpportunities_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> opportunityServiceImple.getAll());
    }

    @Test
    void test_lookUpOpportunity() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppFound = opportunityServiceImple.lookUpOpportunity(opp1.getId());
            assertEquals(opp1.getId(), oppFound.getId());
            assertEquals(opp1.getDecisionMaker(), oppFound.getDecisionMaker());
            assertEquals(opp1.getProduct(), oppFound.getProduct());
            assertEquals(opp1.getQuantity(), oppFound.getQuantity());
            assertEquals(opp1.getStatus(), oppFound.getStatus());
        } catch (DataNotFoundException | EmptyException e) {
            exception = e;
        }
        assertNull(exception);
    }

    @Test
    void test_lookUpOpportunity_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> opportunityServiceImple.lookUpOpportunity(5));
    }

    @Test
    void test_updateOpportunityStatus() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppUpdated = opportunityServiceImple.updateOpportunityStatus(opp1.getId(), Status.CLOSED_WON);
            assertEquals(opp1.getId(), oppUpdated.getId());
            assertEquals(opp1.getDecisionMaker(), oppUpdated.getDecisionMaker());
            assertEquals(opp1.getProduct(), oppUpdated.getProduct());
            assertEquals(opp1.getQuantity(), oppUpdated.getQuantity());
            assertEquals(Status.CLOSED_WON, oppUpdated.getStatus());
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);

    }

    private void addOpportunitiesToDatasource() {

        var salesRep = new SalesRep("John Doe");
        salesRepRepository.save(salesRep);

        var product = Product.HYBRID;
        var prodQty = 5;

        opp1 = new Opportunity(Status.OPEN, product, prodQty, null, null, salesRep);

        opportunityRepository.save(opp1);

        product = Product.BOX;
        prodQty = 7;

        opp2 = new Opportunity(Status.OPEN, product, prodQty, null, null, salesRep);

        opportunityRepository.save(opp2);
    }
}
