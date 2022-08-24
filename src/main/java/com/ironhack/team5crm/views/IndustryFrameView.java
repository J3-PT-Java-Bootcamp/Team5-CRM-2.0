package com.ironhack.team5crm.views;

import com.ironhack.team5crm.services.OpportunityServiceImple;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class IndustryFrameView extends JFrame implements ActionListener, Operations {

    @Autowired
    OpportunityServiceImple opportunityServiceImple;

    private JLabel title, text;
    private JTextField field;
    private JButton exit, search;

    public IndustryFrameView() {

        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p style = 'color : red;'>Reports By Industry<p></html>");
        title.setBounds(200, 20, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p><b>[Report Opportunity by Industry]</b> -> A count of all Opportunities by Industry. <p>" +
                        "<p><b>[Report CLOSED-WON by Industry]</b> -> A count of all CLOSED_WON Opportunities by Industry. <p>" +
                        "<p><b>[Report CLOSED-LOST by Industry]</b> -> A count of all CLOSED-LOST Opportunities by Industry. <p>" +
                        "<p><b>[Report OPEN by Industry]</b> -> A count of all OPEN Opportunities by Industry. <p></html>"
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
        cleanUp();
        setSize(700, 500);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
    }

    //this method just clean the JText in every use
    public void cleanUp() {
        field.setText("");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "exit" -> dispose();
            case "search" -> {
                checkTheText(field.getText());
            }
        }
        cleanUp();
        dispose();
    }


    public void checkTheText(String any) throws EmptyException, WrongInputException {
        cleanUp();
        var extension = any.toLowerCase().split(" ");
        //CALL TO THE EXCEPTION FOR CHECK THE EXTENSION
        if (extension.length <= 3) {
            throw new WrongInputException();
        }

        //CALL TO METHOD FOR CHECK THE SPECIFIC SINTAX
        String toVerified = String.valueOf(verifiedInput(extension));
        String stats = extension[1];
        String industry = extension[3];

        switch (toVerified) {
            case OPPORTUNITY -> JOptionPane.showMessageDialog(null, opportunityServiceImple.counterOpportunitiesByCity(industry));
            case OPEN, CLOSE_LOST, CLOSE_WON -> JOptionPane.showMessageDialog(null, opportunityServiceImple.reportByCity(stats, industry));
            default -> JOptionPane.showMessageDialog(null, "only a valid option, check your sintax");
        }
        dispose();
    }

    //METHOD FOR CHECK THE SPECIFIC SINTAX
    public StringBuilder verifiedInput(String[] estend) {
        StringBuilder getting = new StringBuilder();
        for (int i = 0; i < estend.length - 1; i++) {
            getting.append(estend[i] + " ");
        }
        return getting;
    }
}