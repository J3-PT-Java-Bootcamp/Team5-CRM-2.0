package com.ironhack.team5crm.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.SalesRepServiceImple;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.enums.Industry;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.services.AccountServiceImple;
import com.ironhack.team5crm.services.LeadServiceImple;
import com.ironhack.team5crm.services.OpportunityServiceImple;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import com.ironhack.team5crm.ui.panes.TeamPane;
import com.ironhack.team5crm.ui.panes.TeamPaneDialog;
import com.ironhack.team5crm.ui.panes.TeamScrollPaneContent;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class Menu implements ConsoleOperations {

    TeamPane teamPane = new TeamPane();
    @Autowired
    private LeadServiceImple leadServiceImple;
    @Autowired
    private OpportunityServiceImple opportunityServiceImple;
    @Autowired
    private AccountServiceImple accountServiceImple;
    @Autowired
    private SalesRepServiceImple salesRepServiceImpl;
    private SalesRep salesRepLoggedIn;



    /**
     *  MAIN METHOD OF THE MENU
    *****************************************************************************************************************
     */
    public void main(SalesRep salesRep) throws WrongInputException, AbortedException {

        salesRepLoggedIn = salesRep;
        FlatLightLaf.setup();
        String input;

        // This Menu had to be formatted to html in order for the JOptionPane to display it correctly
        String mainMenu =
            "<html>\n" +
            "<p>  ====================================================================================================== </p>\n" +
            "<body>\n" +
            "<h3>\uD83E\uDD16\uD83D\uDCAC Welcome %s to  </h3>\n" +
            "<h1> Team 5 CRM </h1>\n" +
            "<p>  </p>\n" +
            "<p>  </p>\n" +
            "\n" +

            "<p><b>AVAILABLE OPERATIONS: </b></p>\n" +
            "<p>====================</p>\n" +
            "<ul style=\"font-family:'menlo'\">\n" +
            "<li><b>  new lead   </b>.................> create a new Lead </li>\n" +
            "<li><b>  show leads   </b>..............-> show all leads </li>\n" +
            "<li><b>  lookup lead id   </b>...........> look up a lead by ID </li>\n" +
            "<li><b>  convert id   </b>..............-> convert lead into Opportunity </li>\n" +
            "<li><b>  show opportunities   </b>......-> show all available opportunities </li>\n" +
            "<li><b>  lookup opportunity id   </b>...-> look up an opportunity by it's ID </li>\n" +
            "<li><b>  open id   </b>.................-> sets the opportunity status to open </li>\n" +
            "<li><b>  close-lost id   </b>...........-> sets the opportunity status to CLOSE / LOST </li>\n" +
            "<li><b>  close-won id   </b>............-> sets the opportunity status to CLOSE / WON </li>\n" +
            "<li><b>  show accounts   </b>...........-> show all available accounts </li>\n" +
            "\n" +
            "</ul>\n" +
            "<p>  </p>\n" +

            "<p><b>ADMIN OPERATIONS: </b></p>\n" +
            "<p>====================</p>\n" +
            "<ul style=\"font-family:'menlo'\">\n" +
            "<li><b>  new salesrep  </b>...-> creates a new sales rep</li>\n" +
            "<li><b>  show salesrep  </b>..-> creates a new sales rep </li>\n" +
            "</ul>\n" +
            "\n" +
            "<p>  </p>\n" +

            "<p><b>NOTE: </b></p>\n" +
            "<p>====================</p>\n" +
            "<ul style=\"font-family:'menlo'\">\n" +
            "<li><b>  exit </b>...-> to Exit CRM </li>\n" +
            "</ul>\n" +
            "<p>  </p>\n" +
            "<p>When the command has 'id', replace it with the id of the lead or opportunity you want to work with </p>\n" +
            "<p>  ====================================================================================================== </p>\n" +
            "</body></p>\n" +
            "</body>\n" +
            "</html>\n";

        mainMenu = mainMenu.formatted(salesRepLoggedIn.getName());

        //Then the html formatted text is added to a label, which can interpret the html code
        JLabel menuLabel = new JLabel(mainMenu);

        /* MENU AND LOGIC */
        //**************************************************************************************************************
        do {
            /* USER INPUT AND MENU DISPLAY: */
            input = (String) teamPane.showInputDialog("Team 5 - CRM", menuLabel, 3);
            var inputSplit = input.toLowerCase().split(" ");

            /* THIS IS THE LOGIC OF THE MENU: */
            try {
                switch (inputSplit[0]) {
                    case NEW -> newMenu(inputSplit);
                    case SHOW -> showMenu(inputSplit);
                    case LOOKUP -> lookupMenu(inputSplit);
                    case CONVERT -> convertMenu(inputSplit);
                    case OPEN -> openMenu(inputSplit);
                    case CLOSE_LOST -> closeLostMenu(inputSplit);
                    case CLOSE_WON -> closeWonMenu(inputSplit);
                    case "exit" -> {
                        System.out.println("Adeu");
                        System.exit(1);
                    }
                    default -> System.out.println("Command not recognized!");
                }
            } catch (WrongInputException e) {
                teamPane.showNotFoundDialog(TeamPaneDialog.COMMAND_NOT_RECOGNIZED);
            } catch (DataNotFoundException | AbortedException e) {
                teamPane.showNotFoundDialog(TeamPaneDialog.DATA_NOT_FOUND);
            }
        } while (!input.equals("exit"));
    }
    // ++ END OF MAIN METHOD **
    //**************************************************************************************************************


    // STATUS UPDATERS
    //**************************************************************************************************************
    /**
     * This menu method is for setting the status of an opportunity to OPEN
     */
    private void openMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityServiceImple.updateOpportunityStatus(id, Status.OPEN);
        teamPane.showOppStatusUpdate(TeamPaneDialog.OPP_STATUS_OPEN);
    }

    /** This menu method is for setting the status of an opportunity to CLOSE_LOST */
    private void closeLostMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityServiceImple.updateOpportunityStatus(id, Status.CLOSED_LOST);
        teamPane.showOppStatusUpdate(TeamPaneDialog.OPP_STATUS_CLOSED_LOST);

    }

    /** This menu method is for setting the status of an opportunity to CLOSE_WON */
    private void closeWonMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityServiceImple.updateOpportunityStatus(id, Status.CLOSED_WON);
        teamPane.showOppStatusUpdate(TeamPaneDialog.OPP_STATUS_CLOSED_WON);

    }

    // 'LOOK UP' MENUS
    // **********************************************************

    /** This method is for selecting the "lookup menu" desired by the user according to user's input */
    private void lookupMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 2) {
            throw new WrongInputException();
        }

        int id = Integer.parseInt(inputSplit[2]);
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> lookUpLead(id);
            case ConsoleOperationEntities.OPPORTUNITY -> lookUpOpportunity(id);
            default -> throw new WrongInputException();
        }
    }

    /** This method handles the 'lookup opportunity' menu */
    private void lookUpOpportunity(int id) {
        try {
            teamPane.showMessageDialog("Opportunities " + id, opportunityServiceImple.lookUpOpportunity(id), 1);
        } catch (DataNotFoundException e) {
            teamPane.showMessageDialog("Not Found", "Opportunity with ID " + id + " was not found in the Database!", 2);
        } catch (EmptyException e) {
            teamPane.showMessageDialog("Not Found", "No Opportunities in the database!", 2);
        }
    }

    /** This method handles the 'lookup leads' menu */
    private void lookUpLead(int id) {
        try {
            teamPane.showMessageDialog("Lead " + id, leadServiceImple.lookUpLead(id),  1);
        } catch (EmptyException e) {
            teamPane.showMessageDialog("Not Found",  "No leads in Database!", 2);
        } catch (DataNotFoundException e) {
            teamPane.showMessageDialog("Not Found",  "The Lead with ID " + id + " was not found in the database!", 2);
        }
    }


    // 'SHOW' MENUS
    //**************************************************************************************************************

    /** This method is for selecting the "show menu" desired by the user according to user's input */
    private void showMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEADS -> showLeads();
            case ConsoleOperationEntities.OPPORTUNITIES -> showOpportunities();
            case ConsoleOperationEntities.ACCOUNTS -> showAccounts();
            case ConsoleOperationEntities.SALES_REP -> showSalesRep();
            default -> throw new WrongInputException();
        }
    }

    /** This method handles the 'show leads' menu */
    private void showLeads() {
        try {
            var leadsList = leadServiceImple.getAll();
            teamPane.showScrollPaneFor(TeamScrollPaneContent.LEADS,leadsList);
        } catch (EmptyException e) {
            teamPane.showNotFoundDialog(TeamPaneDialog.LEADS_NOT_FOUND);
        }
    }
    /** This method handles the 'show opportunities' menu */
    private void showOpportunities() {
        try {
            var opportunitiesList = opportunityServiceImple.getAll();
            teamPane.showScrollPaneFor(TeamScrollPaneContent.OPPORTUNITIES,opportunitiesList);
        } catch (EmptyException e) {
            teamPane.showNotFoundDialog(TeamPaneDialog.OPPORTUNITIES_NOT_FOUND);
        }
    }

    /** This method handles the 'show accounts' menu */
    private void showAccounts() {
        try {
            var accountsList = accountServiceImple.getAll();
            teamPane.showScrollPaneFor(TeamScrollPaneContent.ACCOUNTS,accountsList);
        } catch (EmptyException e) {
            teamPane.showNotFoundDialog(TeamPaneDialog.ACCOUNTS_NOT_FOUND);
        }
    }

    /** This method handles the 'show salesRep' menu */
    public void showSalesRep() {
        try {
            var salesRepsList = salesRepServiceImpl.getAll();
            teamPane.showScrollPaneFor(TeamScrollPaneContent.SALESREPS,salesRepsList);
        } catch (EmptyException e) {
            teamPane.showNotFoundDialog(TeamPaneDialog.SALESREPS_NOT_FOUND);
        }
    }

    // CONVERT MENUS
    //**************************************************************************************************************

    /** This method converts a lead into an opportunity and contact */
    private void convertMenu(String[] inputSplit) throws WrongInputException, AbortedException {
        // check if the input is right
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        // get commandos
        int id = Integer.parseInt(inputSplit[1]);

        try {
            // checks that the lead id is valid, if not throws DataNotFoundException
            Lead leadFound = leadServiceImple.lookUpLead(id);

            // asks if user wants to associate this lead conversion to an existing account
            int associateToAccount = teamPane.showConfirmDialog(
                    "Convert or Associate",
                    "Do you want to associate this lead conversion to an existing account?",
                     JOptionPane.YES_NO_OPTION);

            try {
                Account leadsAccount = null;
                // if user wants to associate to an existing account, we ask for its id
                if (associateToAccount == 0) {
                    int accountId = Integer.parseInt((String) getValues("Input the Account ID").get(0));

                    // checks that the account id is valid, if not throws DataNotFoundException
                    leadsAccount = accountServiceImple.lookUpAccount(accountId);
                }

                // asks for the opportunity details
                var product = getProduct();
                int productQty = Integer.parseInt((String) getValues("Quantity?").get(0));

                // if the user wanted to create a new account, asks for its details
                if (leadsAccount == null) {
                    var industry = getIndustry();
                    int employees = Integer.parseInt((String) getValues("Number of employees?").get(0));
                    String city = (String) getValues("City?").get(0);
                    String country = (String) getValues("Country?").get(0);
                    leadsAccount = new Account(industry, employees, city, country);
                }

                // sends the data to be converted
                leadServiceImple.convert(leadFound, product, productQty, leadsAccount);
                teamPane.showMessageDialog("Converted","Lead Successfully converted",1);

            } catch (EmptyException | DataNotFoundException e) {
                teamPane.showMessageDialog("Not Found", "The Account with ID " + id + " was not found in the database!",2);
            }

        } catch (EmptyException e) {
            teamPane.showNotFoundDialog(TeamPaneDialog.LEADS_NOT_FOUND);
        } catch (DataNotFoundException e) {
            teamPane.showMessageDialog("Not Found", "The Lead with ID " + id + " was not found in the database!",2);
        }
    }

    // 'NEW' MENUS
    //**************************************************************************************************************
    private void newMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> {
                List<Object> values = getValues("Name :</br>", "Phone number : </br>", "Email : </br>", "Company : ");
                Lead lead = leadServiceImple.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2),
                        (String) values.get(3), salesRepLoggedIn);
                teamPane.showMessageDialog("Lead Added", "Lead successfully added: </br>" + lead,1);
            }
            case ConsoleOperationEntities.SALES_REP -> newSalesRep();
            default -> throw new WrongInputException();
        }
    }

    public void newSalesRep() throws WrongInputException {
        List<Object> values = getValues("Name :");
        SalesRep salesRep = salesRepServiceImpl.newSalesRep((String) values.get(0));
        teamPane.showMessageDialog("Sales Representative Added", "SalesRep successfully created: </br>" + salesRep,1);
    }

    // OTHER MENUS METHODS
    //**************************************************************************************************************


    // ******************* USING VARARGS FOR REUSING METHODS
    public  List<Object> getValues(Object... values) throws WrongInputException {
        List<Object> value = new ArrayList<>();
        for (var i : values) {
            try {
                value.add(teamPane.showInputDialog("Input", i, JOptionPane.QUESTION_MESSAGE));
            } catch (Exception e) {
                throw new WrongInputException("1");
            }
        }
        return value;
    }

    /**
     * Opens a Dropdown menu that gives the user the options to select a status
     * This method returns a status accordingly to users selection
     */
    public Status getStatus() throws AbortedException {

        String status;
        // These are the options for the dropdown menu
        String[] options = { OPEN, CLOSE_WON, CLOSE_LOST };
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select status to set new status";

        // opens a dropdown menu
        status = (String) teamPane.showInputDialog(
                "Status Update",
                message,
                JOptionPane.QUESTION_MESSAGE,
                options,
                "select");

        // logic
        switch (status) {
            case CLOSE_LOST -> {
                return Status.CLOSED_LOST;
            }
            case OPEN -> {
                return Status.OPEN;
            }
            case CLOSE_WON -> {
                return Status.CLOSED_WON;
            }
            default -> throw new AbortedException();
        }
    }

    /**
     * Opens a Dropdown menu that gives the user the options to select a product
     * This method returns a product accordingly to users selection
     */
    public Product getProduct() throws AbortedException {

        String product;
        // these are the options for the dropdown menu
        String[] options = { HYBRID, FLATBED, BOX };

        // this is the message displayed on the window with the dropdown menu
        String message = "Please select a product";

        // opens a dropdown menu
        product = (String) teamPane.showInputDialog(
                "Product",
                message,
                JOptionPane.QUESTION_MESSAGE,
                options,
                "select");

        // Logic:
        try {
            switch (product) {
                case HYBRID -> {
                    return Product.HYBRID;
                }
                case FLATBED -> {
                    return Product.FLATBED;
                }
                case BOX -> {
                    return Product.BOX;
                }
                default -> throw new AbortedException();
            }
        } catch (Exception e) {
            throw new AbortedException();
        }
    }

    /**
     * Opens a Dropdown menu that gives the user the options to select an industry
     * This method returns an industry accordingly to users selection
     */
    public Industry getIndustry() throws AbortedException {
        String industry;

        // these are the options for the dropdown menu
        String[] options = { PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, OTHER };
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select an industry";

        // opens a dropdown menu
        industry = (String) teamPane.showInputDialog(
                "Industry",
                message,
                JOptionPane.QUESTION_MESSAGE,
                options,
                "select");

        // Logic:
        switch (industry) {
            case PRODUCE -> {
                return Industry.PRODUCE;
            }
            case ECOMMERCE -> {
                return Industry.ECOMMERCE;
            }
            case MANUFACTURING -> {
                return Industry.MANUFACTURING;
            }
            case MEDICAL -> {
                return Industry.MEDICAL;
            }
            case OTHER -> {
                return Industry.OTHER;
            }
            default -> throw new AbortedException();
        }
    }

}
