package com.ironhack.team5crm.domain;

import com.ironhack.team5crm.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ironhack.team5crm.models.enums.Industry;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;

import java.util.List;

/**
 * This test class is just to see what the output will look like once printed
 */
class DomainToStringTests {

    SalesRep testSalesRep;
    Lead testLead;
    List<Contact> testContacts;
    List<Opportunity> testOpportunities;
    Account testAccount;

    // SETUP FOR THE TESTS
    // ***********************************************
    @BeforeEach
    @DisplayName("Starting the object for the test")
    void setUp() {

        testSalesRep = new SalesRep("John Doe");

        testLead = new Lead("John Anyone", "555 55 55", "john@email.com", "Hello World Comp.", testSalesRep);

        testContacts = List.of(
                new Contact( "Eva Mendez", "555 44 44", "eva@comp.com", null),
                new Contact( "Pedro El Escamoso", "555 44 48", "pedro@comp.com", null),
                new Contact( "Johny Johnzales", "555 32 60", "johny@comp.com", null));

        testOpportunities = List.of(
                new Opportunity(20, testContacts.get(0), Status.OPEN, Product.HYBRID, 20, null, testSalesRep),
                new Opportunity(21, testContacts.get(1), Status.OPEN, Product.HYBRID, 20, null, testSalesRep),
                new Opportunity(22, testContacts.get(2), Status.OPEN, Product.HYBRID, 20, null, testSalesRep));

        testAccount = new Account(100, Industry.ECOMMERCE, 250, "Barcelona", "Spain", testContacts, testOpportunities);
    }

    // TESTS
    // ***********************************************
    @Test
    void lead_TestToString() {
        System.out.println(testLead.toString());
    }

    @Test
    void opportunity_TestToString() {
        System.out.println("ONLY ONE OPPORTUNITY");
        System.out.println(testOpportunities.get(0));
        System.out.println("\n\nMULTIPLE OPPORTUNITIES");
        for (Opportunity opp : testOpportunities) {
            System.out.println(opp);
        }

    }

    @Test
    void contact_TestToString() {
        System.out.println("ONLY ONE CONTACT");
        System.out.println(testContacts.get(0).toString());
        System.out.println("\n\nMULTIPLE CONTACTS");
        for (Contact contact : testContacts) {
            System.out.println(contact.toString());
        }
    }

    @Test
    void account_TestToString() {
        System.out.println(testAccount.toString());

        /*
         * Uncomment to see how multiple accounts would look like once printed
         * System.out.println(testAccount.toString());
         * System.out.println(testAccount.toString());
         */
    }
}
