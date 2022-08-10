package com.ironhack.team5crm.domain;

import com.ironhack.team5crm.models.Contact;
import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.ContactRepository;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OpportunityTest {

    private List<Opportunity> opportunities;
    private Opportunity testing;

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    OpportunityRepository opportunityRepository;

    @DisplayName("Setting the start values for instance")
    @BeforeEach
    void setUp() {
        List<Contact> contacts = List.of(
                new Contact( "Arthur Schopenhauer", "555-000-999", "arthurito@fantasymail.com", null),
                new Contact( "Erwin Schrodinger", "555-999-999", "ilovecats@fantasymail.com", null),
                new Contact( "Philo Farnsworth", "555-111-999", "iloveTV@fantasymail.com", null));
        opportunities = List.of(
                new Opportunity(Status.OPEN, Product.HYBRID, 5, contacts.get(0), null, null),
                new Opportunity(Status.CLOSED_LOST, Product.FLATBED, 9, contacts.get(1), null, null),
                new Opportunity(Status.CLOSED_WON, Product.BOX, 15, contacts.get(2), null, null));

        contactRepository.saveAll(contacts);
        opportunityRepository.saveAll(opportunities);

    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    @DisplayName("Check new object was added")
    void getId() {
        testing = opportunities.get(1);
        Assertions.assertNotNull(testing);
        Assertions.assertNotNull(testing.getId());
    }

    @Test
    @DisplayName("Setting values ok")
    void setId() {
        testing = opportunities.get(0);
        Assertions.assertNotNull(testing);
        testing.setId(666);
        var aux = 666;
        Assertions.assertEquals(aux, testing.getId());
    }

    @Test
    @DisplayName("Getting the contact object ok")
    void getDecisionMaker() {
        testing = opportunities.get(2);
        Assertions.assertNotNull(testing);
        Contact cont = testing.getDecisionMaker();
        Assertions.assertEquals(cont.toString(), testing.getDecisionMaker().toString());
    }

    @Test
    @DisplayName("Setting the contact object ok")
    void setDecisionMaker() {
        testing = opportunities.get(2);
        Assertions.assertNotNull(testing);
        Contact cont = testing.getDecisionMaker();
        cont.setId(44);
        Assertions.assertEquals(cont, testing.getDecisionMaker());
    }

    @Test
    @DisplayName("Getting values from ENUM")
    void getStatus() {
        testing = opportunities.get(0);
        var example = Status.OPEN;
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getStatus());
    }

    @Test
    @DisplayName("Setting values from ENUM")
    void setStatus() {
        testing = opportunities.get(0);
        var example = Status.CLOSED_WON;
        testing.setStatus(Status.CLOSED_WON);
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getStatus());
    }

    @Test
    @DisplayName("Getting values from ENUM")
    void getProduct() {
        testing = opportunities.get(0);
        var example = Product.HYBRID;
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getProduct());
    }

    @Test
    @DisplayName("Setting values from ENUM")
    void setProduct() {
        testing = opportunities.get(0);
        var example = Product.HYBRID;
        testing.setProduct(Product.BOX);
        Assertions.assertNotNull(testing);
        Assertions.assertNotEquals(example, testing.getProduct());
    }

    @Test
    @DisplayName("Getting values from Quantity")
    void getQuantity() {
        testing = opportunities.get(1);
        var quanty = 9;
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(quanty, testing.getQuantity());
    }

    @Test
    @DisplayName("Setting values from Quantity")
    void setQuantity() {
        testing = opportunities.get(1);
        var quanty = 99;
        testing.setQuantity(99);
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(quanty, testing.getQuantity());
    }
}
