package com.ironhack.team5crm.views;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class PrincipalView extends JFrame implements ActionListener {

    @Autowired
    private Menu menu;

    @Autowired
    AdminSection adminSection;

    private SalesRep salesRep;

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    private JButton adminButton; // -- check the admind options
    private JButton salesButton; // check the sales option, last project

    private JLabel text, title, image;

    //**** Contructor for start every controller
    public PrincipalView() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //ADD THE TITLE AND WELCOME SECTION
        title = new JLabel("CRM");
        text = new JLabel();
        text.setText("Please, choice any option for continue.");

        //ADD THE ICON
        image = new JLabel(teamIcon);

        //ADD THE LOCATION FOR TEXT
        title.setBounds(180, 40, 200, 30);
        title.setFont(new Font("Courier New", 1, 25));
        title.setForeground(Color.gray);

        image.setBounds(280, 15, 150, 150);

        text.setBounds(80, 110, 350, 100);
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setForeground(Color.gray);



        // ADMIN BUTTON VALUES
        adminButton = new JButton();
        adminButton.setBounds(66, 220, 154, 25);
        adminButton.setText("Admin"); // *** check for a better name
        adminButton.setFont(new Font("Courier New", Font.PLAIN, 12));
        adminButton.setForeground(Color.WHITE);
        adminButton.setBackground(Color.gray);
        adminButton.setBorder(BorderFactory.createEtchedBorder());

        // SALES BUTTON VALUES
        salesButton = new JButton();
        salesButton.setBounds(240, 220, 154, 25);
        salesButton.setText("Sales");
        salesButton.setFont(new Font("Courier New", Font.PLAIN, 12));
        salesButton.setForeground(Color.WHITE);
        salesButton.setBackground(Color.gray);
        salesButton.setBorder(BorderFactory.createLoweredSoftBevelBorder());

        // add the action for every button
        adminButton.addActionListener(this);
        salesButton.addActionListener(this);

        //TAKING THE BUTTONS ON PANECONTAINER
        getContentPane().add(title);
        getContentPane().add(text);
        getContentPane().add(image);
        getContentPane().add(adminButton);
        getContentPane().add(salesButton);


        //CUSTOM UI VALUES TO FRAME
        getContentPane().setBackground(Color.WHITE);

        //ADDING CUSTOM VALUES TO FRAME
        setSize(450, 350);
        setTitle("From 5 To 3 CRM");
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
                adminSection.setVisible(true);
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

        setVisible(false);
    }
}
