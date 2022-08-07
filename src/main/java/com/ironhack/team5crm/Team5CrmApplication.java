package com.ironhack.team5crm;

import com.formdev.flatlaf.FlatLightLaf;
import com.ironhack.team5crm.data.AccountRepository;
import com.ironhack.team5crm.data.ContactRepository;
import com.ironhack.team5crm.data.LeadRepository;
import com.ironhack.team5crm.data.OpportunityRepository;
import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.data.datasources.impl.JsonDatasource;
import com.ironhack.team5crm.services.LeadService;
import com.ironhack.team5crm.services.OpportunityService;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class Team5CrmApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Team5CrmApplication.class)
                .headless(false).run(args);

//        SpringApplication.run(Team5CrmApplication.class, args);

        // The Swing application must be placed on the Swing Event Queue
        // https://stackoverflow.com/a/43684747 -> needs more research to know why
//        EventQueue.invokeLater(() -> {
//
//            try {
//                launchUI();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        });

    }

    private static void launchUI() throws IOException, WrongInputException, AbortedException {
        FlatLightLaf.setup();

        // Setup datasources

        // Uncomment this next line to use json instead of memory
        // Datasource datasource = InMemoryDatasource.getInstance();

        Datasource datasource = JsonDatasource.getInstance();

        // Setup repositories
        OpportunityRepository opportunityRepository = OpportunityRepository.getInstance(datasource);
        AccountRepository accountRepository = AccountRepository.getInstance(datasource);
        LeadRepository leadRepository = LeadRepository.getInstance(datasource);

        // Setup services
        OpportunityService opportunityService = OpportunityService.getInstance(opportunityRepository);
        ContactRepository contactRepository = ContactRepository.getInstance(datasource);
        LeadService leadService = LeadService.getInstance(leadRepository, contactRepository, accountRepository,
                opportunityRepository);

        // Setup UI
        Menu menu = new Menu(leadService, opportunityService);

        // Start UI
        menu.main();
    }

}
