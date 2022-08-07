package com.ironhack.team5crm.services;

import com.ironhack.team5crm.data.OpportunityRepository;
import com.ironhack.team5crm.domain.Opportunity;
import com.ironhack.team5crm.domain.enums.Product;
import com.ironhack.team5crm.domain.enums.Status;
import com.ironhack.team5crm.domain.exceptions.Team5CrmException;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityServiceTest {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    OpportunityService opportunityService;

    Opportunity opp1;
    Opportunity opp2;

    @BeforeEach
    void setUp() {
        opportunityRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_getAllOpportunities() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var opportunities = opportunityService.getAllOpportunities();
            assertEquals(2, opportunities.size());
        } catch (EmptyException e) {
            exception = e;
        }
        assertNull(exception);

    }

    @Test
    void test_getAllOpportunities_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> opportunityService.getAllOpportunities());
    }

    @Test
    void test_lookUpOpportunity() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppFound = opportunityService.lookUpOpportunity(opp1.getId());
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
        assertThrowsExactly(EmptyException.class, () -> opportunityService.lookUpOpportunity(5));
    }

    @Test
    void test_updateOpportunityStatus() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppUpdated = opportunityService.updateOpportunityStatus(opp1.getId(), Status.CLOSED_WON);
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
        var product = Product.HYBRID;
        var prodQty = 5;

        opp1 = new Opportunity(Status.OPEN, product, prodQty, null, null);

        opportunityRepository.save(opp1);

        product = Product.BOX;
        prodQty = 7;

        opp2 = new Opportunity(Status.OPEN, product, prodQty, null, null);

        opportunityRepository.save(opp2);
    }
}
