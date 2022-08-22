package com.ironhack.team5crm.views;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AdminSection extends JFrame implements ActionListener {

    @Autowired
    AdminFrame adminFrame;

    @Autowired
    ReportingChoicesFrame reportingChoicesFrame;

    private SalesRep salesRep;

    private JButton dashboardButton; // -- check the dashborard by every options
    private JButton adminOptionButton; // check the options for new additions available / new sales rep and show all sales

    private JButton exit;

    //**** Contructor for start every controller
    public AdminSection() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ADMIN BUTTON VALUES
        dashboardButton = new JButton();
        dashboardButton.setBounds(40, 75, 100, 25);
        dashboardButton.setText("Reports"); // *** check for a better name

        // SALES BUTTON VALUES
        adminOptionButton = new JButton();
        adminOptionButton.setBounds(160, 75, 100, 25);
        adminOptionButton.setText("Edit");

        // EXIT BUTTON VALUES
        exit = new JButton();
        exit.setBounds(280, 75, 100, 25);
        exit.setText("Exit");

        // add the action for every button
        dashboardButton.addActionListener(this);
        adminOptionButton.addActionListener(this);
        exit.addActionListener(this);

        //TAKING THE BUTTONS ON PANECONTAINER
        getContentPane().add(dashboardButton);
        getContentPane().add(adminOptionButton);
        getContentPane().add(exit);

        //ADDING CUSTOM VALUES TO FRAME
        setSize(450, 350);
        setTitle("FROM 5 TO 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }


    // **** actions in buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "edit" -> adminFrame.setVisible(true);
            case "exit" -> dispose();
            case "reports" -> reportingChoicesFrame.setVisible(true);
            default -> JOptionPane.showMessageDialog(null, "Just a valid option, please");
        }
    }
}

