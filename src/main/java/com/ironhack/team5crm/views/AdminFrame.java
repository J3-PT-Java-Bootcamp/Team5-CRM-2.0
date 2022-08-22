package com.ironhack.team5crm.views;

import com.ironhack.team5crm.services.SalesRepServiceImpl;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AdminFrame extends JFrame implements ActionListener {

    @Autowired
    SalesRepServiceImpl salesRepServiceImpl;

    @Autowired
    private Menu menu;
    private JLabel text;
    private JTextField textField;
    private JButton search, exit;

    public AdminFrame() {
        //**** JPANE : PART TEXT LABEL
        text = new JLabel();
        text.setText("<html><h2 align = 'center'> ADMIN SECTION<h2></br>" +
                "</br>" +
                "</br>" +
                " <b><h3>[ new salesrep ] </b>  -> creates a new sales rep</br>" +
                "<b> [ show salesrep ] </b> -> show all sales rep </br>" +
                "</br>" +
                "<b> [ exit ] </b>  - to Exit CRM</br>" + "</h3></html>");

        text.setFont(new Font("Courier", Font.PLAIN, 16));
        text.setBounds(70, -50, 300, 330);

        //**** JPANE : PART TEXTFIELD
        textField = new JTextField();
        textField.setText("");
        textField.setBounds(170, 250, 100, 20);//need the clean method

        //**** JPANE : PART BUTTONS
        search = new JButton();
        search.setText("Search"); // we need a better name
        search.setBounds(120, 275, 80, 20);

        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(240, 275, 80, 20);

        //**** JPANE : ADD THE LISTENERS
        search.addActionListener(this);
        exit.addActionListener(this);

        //*** JPANE : PART ADDING OBJECTS TO PANEL

        getContentPane().add(text);
        getContentPane().add(textField);
        getContentPane().add(search);
        getContentPane().add(exit);


        //**** JPANE : SETTING THE SIZE
        //CALL TO CLEANUP METHOD
        cleanUp();
        setSize(450, 400);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }

    //this method just clean the JText in every use
    public void cleanUp(){
        textField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toLowerCase()) {
            case "exit" -> dispose();
            case "search" -> {// this gonna call to the main methods

                if(textField.getText().equalsIgnoreCase("new salesrep")){
                    try {
                        menu.newSalesRep();
                    } catch (WrongInputException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (textField.getText().equalsIgnoreCase("show salesrep")) {
                    JOptionPane.showMessageDialog(null, salesRepServiceImpl.findAllSalesRep());
                } else {
                    JOptionPane.showMessageDialog(null, "Please, check the rigth options");
                }
            }
        }
        dispose();
    }
}
