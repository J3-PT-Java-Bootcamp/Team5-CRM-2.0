package com.ironhack.team5crm.ui.views;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import com.ironhack.team5crm.ui.panes.TeamPane;
import com.ironhack.team5crm.ui.panes.TeamPaneDialog;
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

    TeamPane teamPane = new TeamPane();
    static JPanel panel = new JPanel();
    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    private JButton adminButton; // -- check the admind options
    private JButton salesButton; // check the sales option, last project

    private JLabel text, title, image;

    //**** Contructor for start every controller
    public PrincipalView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //ADD PANEL
        getContentPane().add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(450, 350);



        //SET DEFAULT VIEW-FONT
        String viewFont = "melo";


        //ADD THE ICON
        image = new JLabel(teamIcon);
        image.setBounds(280, 15, 150, 150);


        //ADD THE TITLE AND WELCOME SECTION
        title = new JLabel("5to3 - CRM");
        title.setBounds(66, 40, 350, 100);
        title.setFont(new Font(viewFont, 1, 25));
        title.setForeground(Color.darkGray);


        //ADD THE LOCATION FOR TEXT
        text = new JLabel();
        text.setText("Please, select an option to continue.");
        text.setBounds(66, 160, 350, 50);
        text.setFont(new Font(viewFont, Font.PLAIN, 18));
        text.setForeground(Color.darkGray);


        // ADMIN BUTTON VALUES
        adminButton = new JButton();
        adminButton.setBounds(66, 220, 154, 25);
        adminButton.setText("Admin"); // *** check for a better name
        adminButton.setFont(new Font(viewFont, Font.PLAIN, 12));
        adminButton.setForeground(Color.DARK_GRAY);
        adminButton.setBackground(Color.blue);
        adminButton.setBorder(BorderFactory.createEtchedBorder());

        // SALES BUTTON VALUES
        salesButton = new JButton();
        salesButton.setBounds(240, 220, 154, 25);
        salesButton.setText("Sales");
        salesButton.setFont(new Font(viewFont, Font.PLAIN, 12));
        salesButton.setForeground(Color.darkGray);
        salesButton.setBackground(Color.blue);
        salesButton.setBorder(BorderFactory.createEtchedBorder(1));

        // add the action for every button
        adminButton.addActionListener(this);
        salesButton.addActionListener(this);

        //TAKING THE BUTTONS ON PANELCONTAINER
        panel.add(title);
        panel.add(text);
        panel.add(image);
        panel.add(adminButton);
        panel.add(salesButton);


        //CUSTOM UI VALUES TO FRAME

        //ADDING CUSTOM VALUES TO FRAME
        setSize(450, 350);
        setTitle("5to3 - CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

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
            default -> teamPane.showNotFoundDialog(TeamPaneDialog.COMMAND_NOT_RECOGNIZED);
        }

        setVisible(false);
    }
}
