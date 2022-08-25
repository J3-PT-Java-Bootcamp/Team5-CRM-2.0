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
public class AdminSection extends JFrame implements ActionListener {

    @Autowired
    AdminFrame adminFrame;

    @Autowired
    PrincipalView principalView;

    @Autowired
    ReportingChoicesFrame reportingChoicesFrame;

    private SalesRep salesRep;

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    private JButton dashboardButton; // -- check the dashborard by every options
    private JButton adminOptionButton; // check the options for new additions available / new sales rep and show all sales

    private JLabel title, text, image;
    private JButton exit;

    //**** Contructor for start every controller
    public AdminSection() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //ADD THE TITLE AND WELCOME SECTION
        title = new JLabel("CRM");
        text = new JLabel();
        text.setText("Admin options");

        image = new JLabel(teamIcon);

        //ADD THE LOCATION FOR TEXT
        title.setBounds(185, 60, 200, 30);
        title.setFont(new Font("Courier New", 1, 25));
        title.setForeground(Color.gray);

        image.setBounds(280, 15, 150, 150);

        text.setBounds(145, 80, 200, 100);
        text.setFont(new Font("Courier New", Font.PLAIN, 16));
        text.setForeground(Color.gray);

        // ADMIN BUTTON VALUES
        dashboardButton = new JButton();
        dashboardButton.setBounds(40, 200, 100, 25);
        dashboardButton.setText("Reports"); // *** check for a better name
        dashboardButton.setFont(new Font("Courier New", Font.PLAIN, 12));
        dashboardButton.setForeground(Color.WHITE);
        dashboardButton.setBackground(Color.gray);
        dashboardButton.setBorder(BorderFactory.createEtchedBorder());

        // SALES BUTTON VALUES
        adminOptionButton = new JButton();
        adminOptionButton.setBounds(160, 200, 100, 25);
        adminOptionButton.setText("Edit");
        adminOptionButton.setFont(new Font("Courier New", Font.PLAIN, 12));
        adminOptionButton.setForeground(Color.WHITE);
        adminOptionButton.setBackground(Color.gray);
        adminOptionButton.setBorder(BorderFactory.createEtchedBorder());

        // EXIT BUTTON VALUES
        exit = new JButton();
        exit.setBounds(280, 200, 100, 25);
        exit.setText("Back");
        exit.setFont(new Font("Courier New", Font.PLAIN, 12));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.gray);
        exit.setBorder(BorderFactory.createEtchedBorder());

        // add the action for every button
        dashboardButton.addActionListener(this);
        adminOptionButton.addActionListener(this);
        exit.addActionListener(this);

        //TAKING THE BUTTONS ON PANECONTAINER
        getContentPane().add(title);
        getContentPane().add(text);
        getContentPane().add(image);
        getContentPane().add(dashboardButton);
        getContentPane().add(adminOptionButton);
        getContentPane().add(exit);

        //CUSTOM UI VALUES TO FRAME
        getContentPane().setBackground(Color.WHITE);

        //ADDING CUSTOM VALUES TO FRAME
        setSize(450, 350);
        setTitle("From 5 To 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }


    // **** actions in buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "edit" -> adminFrame.setVisible(true);
            case "back" -> principalView.setVisible(true);
            case "reports" -> reportingChoicesFrame.setVisible(true);
            default -> JOptionPane.showMessageDialog(null, "Just a valid option, please");
        }
    }
}

