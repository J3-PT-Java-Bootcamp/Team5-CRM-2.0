package com.ironhack.team5crm.domain;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.repositories.LeadRepository;
import com.ironhack.team5crm.repositories.SalesRepRepository;
import org.junit.jupiter.api.*;

import com.ironhack.team5crm.models.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LeadTest {

    private List<Lead> leads;

    private SalesRep salesRep;
    private Lead lead;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @DisplayName("Adding instances of Leads ")
    @BeforeEach
    void setUp() {
        salesRep = new SalesRep("John Doe");
        salesRepRepository.save(salesRep);
        leads = List.of(
                new Lead( "Arthur Schopenhauer", "555-000-999", "arthurito@fantasymail.com", "The world like representation", salesRep),
                new Lead( "Erwin Schrodinger", "555-999-999", "ilovecats@fantasymail.com", "The box man", salesRep),
                new Lead( "Philo Farnsworth", "555-111-999", "iloveTV@fantasymail.com", "Coffes and tvs", salesRep));

        leadRepository.saveAll(leads);

    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    @DisplayName("Getting the id from a Lead")
    void getId() {
        lead = leads.get(0);
        Assertions.assertNotNull(lead);
        Assertions.assertNotNull(lead.getId());
    }

    @Test
    @DisplayName("Setting the id of instance")
    void setId() {
        lead = leads.get(0);
        lead.setId(99);
        var aux = 99;
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getId());
    }

    @Test
    @DisplayName("Getting name from the instance")
    void getName() {
        lead = leads.get(2);
        var aux = "Philo Farnsworth";
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getName());
    }

    @Test
    @DisplayName("Setting name from lead ok")
    void setName() {
        lead = leads.get(1);
        lead.setName("Ludwig Van Beethoven");
        var aux = "Ludwig Van Beethoven";
        Assertions.assertNotNull(lead);
        Assertions.assertTrue(aux.equalsIgnoreCase(lead.getName()));
    }

    @Test
    @DisplayName("Getting phone from the instance lead")
    void getPhoneNumber() {
        lead = leads.get(2);
        var aux = "555-111-999";
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getPhoneNumber());
    }

    @Test
    @DisplayName("Setting Phone from lead ok")
    void setPhoneNumber() {
        lead = leads.get(1);
        lead.setPhoneNumber("1-777-99-0");
        var aux = "1-777-99-0";
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getPhoneNumber());
    }

    @Test
    @DisplayName("Getting Email from the instance")
    void getEmail() {
        lead = leads.get(0);
        var aux = "arthurito@fantasymail.com";
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getEmail());
    }

    @Test
    @DisplayName("Setting Email from lead ok")
    void setEmail() {
        lead = leads.get(1);
        var aux = "imaginarymail@fantasymail.com";
        lead.setEmail("imaginarymail@fantasymail.com");
        Assertions.assertNotNull(lead);
        Assertions.assertEquals(aux, lead.getEmail());
    }

    @Test
    @DisplayName("Getting the Company name from lead ok")
    void getCompanyName() {
        lead = leads.get(2);
        var aux = "Coffes and tvs";
        Assertions.assertNotNull(lead);
        Assertions.assertTrue(aux.equalsIgnoreCase(lead.getCompanyName()));
    }

    @Test
    @DisplayName("Setting the Company name from lead ok")
    void setCompanyName() {
        lead = leads.get(0);
        var aux = "Video Killed the radio stars";
        lead.setCompanyName(aux);
        Assertions.assertNotNull(lead);
        Assertions.assertTrue(aux.equalsIgnoreCase(lead.getCompanyName()));
    }
}
