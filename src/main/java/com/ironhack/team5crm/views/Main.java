package com.ironhack.team5crm.views;

import com.ironhack.team5crm.controllers.AccountController;
import com.ironhack.team5crm.controllers.LeadController;
import com.ironhack.team5crm.controllers.OpportunityController;
import com.ironhack.team5crm.controllers.SalesRepController;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.servicesImplements.AccountServiceImple;
import com.ironhack.team5crm.services.servicesImplements.LeadServiceImple;
import com.ironhack.team5crm.services.servicesImplements.OpportunityServiceImpl;
import com.ironhack.team5crm.services.servicesImplements.SalesRepServiceImple;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {

    //STARTING VALUES FOR EVERY COMPONENT IN THE APP
    @Autowired
    AccountServiceImple accountServiceImple;
    @Autowired
    LeadServiceImple leadServiceImple;
    @Autowired
    OpportunityServiceImpl opportunityService;
    @Autowired
    SalesRepServiceImple salesRepServiceImple;
    SalesRep empty;

    @Autowired
    PrincipalView principalView;

    @Autowired
    AccountController accountController;
    @Autowired
    LeadController leadController;
    @Autowired
    OpportunityController opportunityController;
    @Autowired
    SalesRepController salesRepController;

    @Autowired
    LoginFrame login;
    @Autowired
    Menu menu;

    public static void main(String g []) throws AbortedException, WrongInputException {
        Main test = new Main();
        test.getStartValues();
    }


    private void getStartValues() {

        accountServiceImple = new AccountServiceImple();
        leadServiceImple = new LeadServiceImple();
        opportunityService = new OpportunityServiceImpl();
        //salesRepServiceImple = new SalesRepServiceImple();
        principalView = new PrincipalView();
        accountController = new AccountController();
        leadController = new LeadController();
        opportunityController = new OpportunityController();
        salesRepController = new SalesRepController();

        login = new LoginFrame();
        menu = new Menu();
        empty = new SalesRep();


        //start relationships;

        principalView.setDirector(menu, login, empty);

        login.setdirector(salesRepServiceImple, menu, empty);
        //menu.main(empty);

        principalView.setVisible(true);

    }
}
