package com.ironhack.team5crm.views;

import com.ironhack.team5crm.controllers.AccountController;
import com.ironhack.team5crm.controllers.LeadController;
import com.ironhack.team5crm.controllers.OpportunityController;
import com.ironhack.team5crm.controllers.SalesRepController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame implements ActionListener {

    private SalesRepController salesRepController;
    private AccountController accountController;
    private OpportunityController opportunityController;
    private LeadController leadController;
    private JButton adminButton; // -- check the admind options
    private JButton salesButton; // check the sales option, last project

    //**** Contructor for start every controller
    public Principal(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ADMIN BUTTON VALUES
        adminButton = new JButton();
        adminButton.setBounds(66, 75, 154, 25);
        adminButton.setText("Admin"); // *** check for a better name

        // SALES BUTTON VALUES
        salesButton = new JButton();
        salesButton.setBounds(240, 75, 154, 25);
        salesButton.setText("Sales");

        // add the action for every button
        adminButton.addActionListener(this);
        salesButton.addActionListener(this);

        //TAKING THE BUTTONS ON PANECONTAINER
        getContentPane().add(adminButton);
        getContentPane().add(salesButton);

        //ADDING CUSTOM VALUES TO FRAME
        setSize(450, 350);
        setTitle("FROM 5 TO 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        setVisible(true);

    }

    //declaring start values for controllers
    public  void setDirector(SalesRepController salesRepController, AccountController accountController, OpportunityController opportunityController, LeadController leadController){
        this.salesRepController = salesRepController;
        this.opportunityController = opportunityController;
        this.accountController = accountController;
        this.leadController = leadController;
    }

    // **** actions in buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Admin" -> {

            }
            case "Sales" -> {

            }
            default -> JOptionPane.showMessageDialog(null, "Just a valid option, please");
        }

        if (e.getSource() == salesButton) {
           // salesRepController.showFrameRep(); // ***THIS GOING TO PAST MENU, CHECK THE CLASS
        }
        else if (e.getSource() == adminButton){
           // salesRepController.showFrameRep();
        }
    }

    public static void main(String [] h) {
        Principal pri = new Principal();
    }
}
