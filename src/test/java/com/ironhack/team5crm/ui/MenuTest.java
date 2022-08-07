package com.ironhack.team5crm.ui;

import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.LeadRepository;
import com.ironhack.team5crm.data.OpportunityRepository;
import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.data.datasources.impl.JsonDatasource;
import com.ironhack.team5crm.domain.enums.Industry;
import com.ironhack.team5crm.domain.enums.Product;
import com.ironhack.team5crm.domain.enums.Status;
import com.ironhack.team5crm.services.LeadService;
import com.ironhack.team5crm.services.OpportunityService;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    Datasource datasource = JsonDatasource.getInstance();

    OpportunityRepository opportunityRepository = OpportunityRepository.getInstance(datasource);
    OpportunityService opportunityService = OpportunityService.getInstance(opportunityRepository);

    AccountRepository accountRepository = AccountRepository.getInstance(datasource);
    LeadRepository leadRepository = LeadRepository.getInstance(datasource);
    ContactRepository contactRepository = ContactRepository.getInstance(datasource);

    LeadService leadService = LeadService.getInstance(leadRepository, contactRepository, accountRepository, opportunityRepository);

    Menu menu = new Menu(leadService, opportunityService);

    MenuTest() throws IOException {

    }

    @Test
    void getStatus_open() throws AbortedException {
        //select open on the menu

        var testStatus = menu.getStatus();
        System.out.println(testStatus);
        assertEquals(Status.OPEN,testStatus);
    }
    @Test
    void getStatus_close_won() throws AbortedException {
        // select closed_won on the menu

        var testStatus = menu.getStatus();
        System.out.println(testStatus);
        assertEquals(Status.CLOSED_WON,testStatus);

    }
    @Test
    void getStatus_close_lost() throws AbortedException {
        // select closed_lost on the menu

        var testStatus = menu.getStatus();
        System.out.println(testStatus);
        assertEquals(Status.CLOSED_LOST,testStatus);
    }

    @Test
    void getProduct_HYBRID() throws AbortedException {
        // select HYBRID on the menu

        var testProduct = menu.getProduct();
        System.out.println(testProduct);

        assertEquals(Product.HYBRID,testProduct);
    }

    @Test
    void getProduct_returnsAProduct() throws AbortedException {
        List<Product> testList = List.of(
                Product.HYBRID,
                Product.FLATBED,
                Product.BOX
        );
        boolean inTheList = false;

        var testProduct = menu.getProduct();

        if (testList.contains(testProduct)){
            inTheList = true;
            System.out.println("User selected: "+testProduct+" which is in the List");
        }

        assertTrue(inTheList);
    }

    @Test
    void getIndustry_returnsAnIndustry() throws AbortedException {
        List<Industry> testList = List.of(
                Industry.PRODUCE,
                Industry.ECOMMERCE,
                Industry.MANUFACTURING,
                Industry.MEDICAL,
                Industry.OTHER
        );
        boolean inTheList = false;

        var testIndustry = menu.getIndustry();

        if (testList.contains(testIndustry)){
            inTheList = true;
            System.out.println("User selected: "+testIndustry+" which is in the List");
        }

        assertTrue(inTheList);
    }
}