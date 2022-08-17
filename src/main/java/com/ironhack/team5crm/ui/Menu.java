package com.ironhack.team5crm.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.servicesImplements.SalesRepServiceImple;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.models.Account;
import com.ironhack.team5crm.models.Lead;
import com.ironhack.team5crm.models.enums.Industry;
import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.services.servicesImplements.AccountServiceImple;
import com.ironhack.team5crm.services.servicesImplements.LeadServiceImple;
import com.ironhack.team5crm.services.servicesImplements.OpportunityServiceImpl;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class Menu implements ConsoleOperations {

    @Autowired
    private LeadServiceImple leadServiceImple;

    @Autowired
    private OpportunityServiceImpl opportunityServiceImpl;

    @Autowired
    private AccountServiceImple accountService;

    @Autowired
    private SalesRepServiceImple salesRepServiceImple;

    private SalesRep salesRepLoggedIn;

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    public void main(SalesRep salesRep) throws WrongInputException, AbortedException {

        salesRepLoggedIn = salesRep;

        FlatLightLaf.setup();

        String input;

        do {
            var mainMenu = """
                    🤖💬 Welcome %s to
                    <html>  <h1> <b> Team 5 CRM </b> </h1>

                    Available Operations:
                    =====================

                    <html> <b> [ new lead ] </b> -> create a new Lead

                    <html> <b> [ show leads ] </b> -> show all leads

                    <html> <b> [ lookup lead id ] </b> -> look up a lead by ID

                    <html> <b> [ convert id ] </b> -> convert a selected lead into a new Opportunity

                    <html> <b> [ show opportunities ] </b> -> show all available opportunities

                    <html> <b> [ lookup opportunity id ] </b> -> look up an opportunity by it's ID

                    <html> <b> [ open id ]  </b> -> sets the opportunity status to open

                    <html> <b> [ close-lost id ] </b> -> sets the opportunity status to CLOSE / LOST

                    <html> <b> [ close-won id ] </b> -> sets the opportunity status to CLOSE / WON

                    <html> <b> [ show acounts ] </b> -> show all available accounts

                    ====================

                    ADMIN SECTION

                    <html> <b> [ new salesrep ] </b> -> creates a new sales rep

                    <html> <b> [ show salesrep ] </b> -> creates a new sales rep

                    ====================

                    <html> <b> [ exit ] </b>  - to Exit CRM
                    ====================

                    When the command has 'id', replace it with the id of the lead or opportunity you want to work with

                    ====================

                    Write your COMMAND:
                    """.formatted(salesRepLoggedIn.getName());
            input = (String) JOptionPane.showInputDialog(null, mainMenu, "Team 5 - CRM", 3, teamIcon, null, null);

            var inputSplit = input.toLowerCase().split(" ");

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
                JOptionPane.showMessageDialog(null, "Command not recognized, please try again. 🤔 ", "Not Found", 2);
            } catch (DataNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Data not found", "Not Found", 2);
            } catch (AbortedException a) {
                JOptionPane.showMessageDialog(null, "Convert aborted", "Aborted", 2);
            }

        } while (!input.equals("exit"));
    }

    // STATUS UPDATERS
    // **********************************************************

    /**
     * This menu method is for setting the status of an opportunity to OPEN
     */
    private void openMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityServiceImpl.updateOpportunityStatus(id, Status.OPEN);
        JOptionPane.showMessageDialog(null, "✏️ Opportunity Status is now 'OPEN': \n" + opportunity, "Status Update",
                1);
    }

    /**
     * This menu method is for setting the status of an opportunity to CLOSE_LOST
     */
    private void closeLostMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityServiceImpl.updateOpportunityStatus(id, Status.CLOSED_LOST);
        JOptionPane.showMessageDialog(null, "🆑 Opportunity Status is now 'CLOSE_LOST': \n" + opportunity,
                "Status Update", 1);
    }

    /**
     * This menu method is for setting the status of an opportunity to CLOSE_WON
     */
    private void closeWonMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityServiceImpl.updateOpportunityStatus(id, Status.CLOSED_WON);
        JOptionPane.showMessageDialog(null, "✅ Opportunity Status is now 'CLOSE_WON': \n" + opportunity,
                "Status Update", 1);
    }

    // 'LOOK UP' MENUS
    // **********************************************************

    /**
     * This method is for selecting the "lookup menu" desired by the user according
     * to user's input
     */
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

    /**
     * This method handles the 'lookup opportunity' menu
     */
    private void lookUpOpportunity(int id) {
        try {
            JOptionPane.showMessageDialog(null, opportunityServiceImpl.lookUpOpportunity(id), "Opportunities " + id, 1);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Opportunity with ID " + id + " was not found in the Database!",
                    "Not Found", 2);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Opportunities in the database!", "Not Found", 2);
        }
    }

    /**
     * This method handles the 'lookup leads' menu
     */
    private void lookUpLead(int id) {
        try {
            JOptionPane.showMessageDialog(null, leadServiceImple.lookUpLead(id), "Lead " + id, 1);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No leads in Database!", "Not Found", 2);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The Lead with ID " + id + " was not found in the database!",
                    "Not Found", 2);
        }
    }

    // 'SHOW' MENUS
    // **********************************************************

    /**
     * This method is for selecting the "show menu" desired by the user according to
     * user's input
     */
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

    /**
     * This method handles the 'show leads' menu
     */
    private void showLeads() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following Leads where found in the database: \n");
            output.append("************************************************\n\n");

            var leads = leadServiceImple.getAllLeads();
            for (var lead : leads) {
                output.append(lead).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Leads in Database", 1, teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Leads in Database!", "Not Found", 2);
        }
    }

    private void showOpportunities() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following Opportunities where found in the database: \n");
            output.append("************************************************\n\n");

            var opps = opportunityServiceImpl.getAllOpportunities();
            for (var opp : opps) {
                output.append(opp.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Opportunities in Database", 1, teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Opportunities in the Database!", "Not Found", 2);
        }
    }

    private void showAccounts() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following Accounts where found in the database: \n");
            output.append("************************************************\n\n");

            var accounts = accountService.getAllAccounts();
            for (var account : accounts) {
                output.append(account.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Accounts in Database", 1, teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Accounts in the Database!", "Not Found", 2);
        }
    }

    private void showSalesRep() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following SalesReps where found in the database: \n");
            output.append("************************************************\n\n");

            var salesReps = salesRepServiceImple.getAllSalesRep();
            for (var salesrep : salesReps) {
                output.append(salesrep.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "SalesReps in Database", 1, teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No SalesReps in the Database!", "Not Found", 2);
        }
    }

    // CONVERT MENUS
    // **********************************************************
    private void convertMenu(String[] inputSplit) throws WrongInputException, AbortedException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);
        try {
            // checks that the lead id is valid, if not throws DataNotFoundException
            Lead leadFound = leadServiceImple.lookUpLead(id);

            // asks if user wants to associate this lead conversion to an existing account
            int associateToAccount = JOptionPane.showConfirmDialog(null,
                    "Do you want to associate this lead conversion to an existing account?",
                    "Associate to an existing account", JOptionPane.YES_NO_OPTION);

            try {
                Account leadsAccount = null;

                // if user wants to associate to an existing account, we ask for its id
                if (associateToAccount == 0) {
                    int accountId = Integer.parseInt((String) getValues("Input the Account ID").get(0));

                    // checks that the account id is valid, if not throws DataNotFoundException
                    leadsAccount = accountService.lookUpAccount(accountId);
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

                JOptionPane.showMessageDialog(null, "Lead Succesfully converted");

            } catch (EmptyException | DataNotFoundException e) {
                JOptionPane.showMessageDialog(null, "The Account with ID " + id + " was not found in the database!",
                        "Not Found", 2);
            }

        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No leads in Database!", "Not Found", 2);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The Lead with ID " + id + " was not found in the database!",
                    "Not Found", 2);
        }
    }

    // 'NEW' MENUS
    // **********************************************************
    private void newMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> {
                List<Object> values = getValues("Name :\n", "Phone number : \n", "Email : \n", "Company : ");
                Lead lead = leadServiceImple.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2),
                        (String) values.get(3), salesRepLoggedIn);
                JOptionPane.showMessageDialog(null, "Lead successfully added: \n" + lead, "Lead Added", 1);
            }
            case ConsoleOperationEntities.SALES_REP -> {
                newSalesRep();
            }
            default -> throw new WrongInputException();
        }
    }

    public void newSalesRep() throws WrongInputException {
        List<Object> values = getValues("Name :");
        SalesRep salesRep = salesRepServiceImple.newSalesRep((String) values.get(0));
        JOptionPane.showMessageDialog(null, "SalesRep successfully created: \n" + salesRep, "SalesRep Added", 1);
    }

    // OTHER MENUS METHODS
    // **********************************************************

    // ******************* USING VARARGS FOR REUSING METHODS
    public static List<Object> getValues(Object... values) throws WrongInputException {
        List<Object> value = new ArrayList<>();
        for (var i : values) {
            try {
                value.add(JOptionPane.showInputDialog(null, i, "Input", JOptionPane.QUESTION_MESSAGE, teamIcon, null,
                        null));
            } catch (Exception e) {
                throw new WrongInputException("1");
            }
        }
        return value;
    }

    /**
     * Opens a dropsdown menu that gives the user the options to select a status
     * This method returns a status accordingly to users selection
     */
    public Status getStatus() throws AbortedException {

        String status;
        // These are the options for the dropdown menu
        String[] options = { OPEN, CLOSE_WON, CLOSE_LOST };
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select status to set new status";

        // opens a dropdown menu
        status = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Status Update",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "---");

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
     * Opens a dropsdown menu that gives the user the options to select a product
     * This method returns a product accordingly to users selection
     */
    public Product getProduct() throws AbortedException {

        String product;
        // these are the options for the dropdown menu
        String[] options = { HYBRID, FLATBED, BOX };

        // this is the message displayed on the window with the dropdown menu
        String message = "Please select a product";

        // opens a dropdown menu
        product = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Product",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "Select");

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
     * Opens a dropsdown menu that gives the user the options to select an industry
     * This method returns an industry accordingly to users selection
     */
    public Industry getIndustry() throws AbortedException {
        String industry;

        // these are the options for the dropdown menu
        String[] options = { PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, OTHER };
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select an industry";

        // opens a dropdown menu
        industry = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Industry",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "---");

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
