package com.ironhack.team5crm.views;

import com.ironhack.team5crm.services.SalesRepServiceImpl;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class SalesRepReportView extends JFrame implements ActionListener {
    @Autowired
    SalesRepServiceImpl salesRepServiceImpl;

    private JLabel title, text;
    private JTextField field;
    private JButton exit, search;

    public SalesRepReportView(){

        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p style = 'color : red;'>Reports By SalesRep<p></html>");
        title.setBounds(200, 20, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p><b>[Report Lead by SalesRep]</b> -> A count of Leads by SalesRep. <p>" +
                        "<p><b>[Report Opportunity by SalesRep]</b> -> A count of all Opportunities by SalesRep. <p>" +
                        "<p><b>[Report CLOSED-WON by SalesRep]</b> -> A count of all CLOSED_WON Opportunities by SalesRep. <p>" +
                        "<p><b>[Report CLOSED-LOST by SalesRep]</b> -> A count of all CLOSED_LOST Opportunities by SalesRep. <p>" +
                        "<p><b>[Report OPEN by SalesRep]</b> -> A count of all OPEN Opportunities by SalesRep. </p></html>"
        );
        text.setBounds(30, -40, 650, 450);
        text.setFont(new Font("Courier New", Font.PLAIN, 12));

        //**** JPANE : PART TEXT FIELD
        field = new JTextField();
        field.setBounds(200, 300, 270, 30);
        field.setFont(new Font("Courier New", Font.BOLD, 15));

        //**** JPANE : PART BUTTONS

        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(250, 340, 80, 30);

        search = new JButton();
        search.setText("Search");
        search.setBounds(340, 340, 80, 30);

        //**** JPANE : PART, CALL THE LISTENERS

        exit.addActionListener(this);
        search.addActionListener(this);

        //**** JPANE : PART ADDING OBJECTS TO PANEL
        getContentPane().add(title);
        getContentPane().add(text);
        getContentPane().add(field);
        getContentPane().add(exit);
        getContentPane().add(search);

        //**** JPANE : PART CUSTOM SIZE

        setSize(700, 500);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
    }


    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()){
            case "exit" -> dispose();
            case "search" -> {
                //try {
                checkTheText(field.getText());
                // } catch (WrongInputException ex) {
                //  throw new RuntimeException(ex);
                //}
            }
        }
        dispose();
    }


    public void checkTheText(String any) throws  EmptyException {
        switch (any.toLowerCase()) {
            case "report lead by salesrep" -> {
                JOptionPane.showMessageDialog(null, salesRepServiceImpl.counterLeadsBySales());
            }
            case "report opportunity by salesrep" -> dispose();
            case "report close-won by salesrep" -> dispose();
            case "report close-lost by salesrep" -> dispose();
            case "report open by salesrep" -> dispose();
        }
    }
    // }
}
