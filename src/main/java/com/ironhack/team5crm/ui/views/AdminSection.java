package com.ironhack.team5crm.ui.views;

import com.ironhack.team5crm.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AdminSection extends JFrame implements ActionListener {

    @Autowired
    SalesRepManagerView salesRepManagerView;

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

        String viewFont = "melo";


        //ADD THE TITLE AND WELCOME SECTION
        title = new JLabel("ADMIN OPTIONS");
        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                "<p>  </p></br>"+
                "<p><b>[ Reports ]:</b>   get some statistics <p>" +
                "<p><b>[ Sales Rep ]:</b> manage sales representatives.<p>"+
                "<p>  </p></br>"+
                "<p>Please, select an option to continue.</p>"+
                "</html>"
        );

        image = new JLabel(teamIcon);

        //ADD THE LOCATION FOR TEXT
        title.setBounds(80, 50, 400, 30);
        title.setFont(new Font(viewFont, 1, 25));
        title.setForeground(Color.DARK_GRAY);

        image.setBounds(500, 15, 130, 130);

        text.setBounds(80, 100, 350, 200);
        text.setFont(new Font(viewFont, Font.PLAIN, 14));
        text.setForeground(Color.darkGray);

        // ADMIN BUTTON VALUES
        dashboardButton = new JButton();
        dashboardButton.setBounds(80, 320, 100, 25);
        dashboardButton.setText("Reports"); // *** check for a better name
        dashboardButton.setFont(new Font(viewFont, Font.PLAIN, 12));
//        dashboardButton.setForeground(Color.DARK_GRAY);
//        dashboardButton.setBackground(Color.blue);
        dashboardButton.setBorder(BorderFactory.createEtchedBorder());

        // SALES BUTTON VALUES
        adminOptionButton = new JButton();
        adminOptionButton.setBounds(186, 320, 100, 25);
        adminOptionButton.setText("Sales Rep.");
        adminOptionButton.setFont(new Font(viewFont, Font.PLAIN, 12));
//        adminOptionButton.setForeground(Color.darkGray);
//        adminOptionButton.setBackground(Color.blue);
        adminOptionButton.setBorder(BorderFactory.createEtchedBorder(1));

        // EXIT BUTTON VALUES
        exit = new JButton();
        exit.setBounds(306, 320, 100, 25);
        exit.setText("Back");
        exit.setFont(new Font(viewFont, Font.PLAIN, 12));
//        exit.setForeground(Color.darkGray);
//        exit.setBackground(Color.darkGray);
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
        setSize(700, 500);
        setTitle("5to3 - CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }


    // **** actions in buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "sales rep." -> salesRepManagerView.setVisible(true);
            case "back" -> principalView.setVisible(true);
            case "reports" -> reportingChoicesFrame.setVisible(true);
            default -> JOptionPane.showMessageDialog(null, "Just a valid option, please");
        }

        setVisible(false);
    }
}

