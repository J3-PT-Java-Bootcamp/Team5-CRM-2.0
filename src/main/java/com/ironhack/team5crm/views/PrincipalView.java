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
public class PrincipalView extends JFrame implements ActionListener {

    @Autowired
    private Menu menu;

    @Autowired
    AdminFrame adminFrame;

    private SalesRep salesRep;

    private JButton adminButton; // -- check the admind options
    private JButton salesButton; // check the sales option, last project

    //**** Contructor for start every controller
    public PrincipalView() {

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

    }

    public void setDirector(SalesRep salesRep) {
        this.salesRep = salesRep;
    }


    // **** actions in buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "admin" -> {
                adminFrame.setVisible(true);
            }
            case "sales" -> {
                try {
                    menu.main(salesRep);//its ok
                } catch (WrongInputException | AbortedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            default -> JOptionPane.showMessageDialog(null, "Just a valid option, please");
        }
    }
}
