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
public class QuantityReportView extends JFrame implements ActionListener, Operations {

    @Autowired
    OpportunityServiceImple opportunityServiceImple;

    @Autowired
    ReportingChoicesFrame reportingChoicesFrame;

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    private JLabel title, text, image;
    private JTextField field;
    private JButton exit, search;

    public QuantityReportView(){

        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p>Reports By Quantity<p></html>");
        title.setBounds(200, 50, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));
        title.setForeground(Color.gray);

        //ADD THE ICON
        image = new JLabel(teamIcon);
        image.setBounds(530, 25, 130, 130);

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p><b>[Mean Quantity]</b> -> The mean Quantity. <p>" +
                        "<p><b>[Median Quantity]</b> -> The median Quantity. <p>" +
                        "<p><b>[Max Quantity]</b> -> The maximum Quantity. <p>" +
                        "<p><b>[Min Quantity]</b> -> The minimum Quantity. <p></html>"
        );
        text.setBounds(80, -40, 650, 450);
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setForeground(Color.gray);

        //**** JPANE : PART TEXT FIELD
        field = new JTextField();
        field.setBounds(200, 300, 270, 30);
        field.setFont(new Font("Courier New", Font.BOLD, 15));

        //**** JPANE : PART BUTTONS

        exit = new JButton();
        exit.setText("Back");
        exit.setBounds(250, 340, 80, 30);
        exit.setFont(new Font("Courier New", Font.PLAIN, 14));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.gray);
        exit.setBorder(BorderFactory.createEtchedBorder());

        search = new JButton();
        search.setText("Search");
        search.setBounds(340, 340, 80, 30);
        search.setFont(new Font("Courier New", Font.PLAIN, 14));
        search.setForeground(Color.WHITE);
        search.setBackground(Color.gray);
        search.setBorder(BorderFactory.createEtchedBorder());


        //**** JPANE : PART, CALL THE LISTENERS

        exit.addActionListener(this);
        search.addActionListener(this);

        //**** JPANE : PART ADDING OBJECTS TO PANEL
        getContentPane().add(title);
        getContentPane().add(image);
        getContentPane().add(text);
        getContentPane().add(field);
        getContentPane().add(exit);
        getContentPane().add(search);

        //CUSTOM UI VALUES TO FRAME
        getContentPane().setBackground(Color.WHITE);

        //**** JPANE : PART CUSTOM SIZE

        setSize(700, 500);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
    }

    //this method just clean the JText in every use
    public void cleanUp(){
        field.setText("");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()){
            case "back" -> reportingChoicesFrame.setVisible(true);
            case "search" -> checkTheText(field.getText());
        }
        setVisible(false);
        reportingChoicesFrame.setVisible(true);
    }


    public void checkTheText(String any) throws EmptyException, WrongInputException {
        cleanUp();
        var extension = any.toLowerCase().split(" ");
        //CALL TO THE EXCEPTION FOR CHECK THE EXTENSION
        if(extension.length < 1){
            throw new WrongInputException();
        }

        //CALL TO METHOD FOR CHECK THE SPECIFIC SINTAX
        String toVerified = String.valueOf(verifiedInput(extension));
        String stats = extension[0];
        String quantity = extension[1];

        switch (toVerified) {
            case MEAN , MEDIAN, MIN, MAX -> JOptionPane.showMessageDialog(null, opportunityServiceImple.statesByOpportunity(stats, quantity));
            default -> JOptionPane.showMessageDialog(null, "only a valid option, check your sintax");
        }
        dispose();
    }

    //METHOD FOR CHECK THE SPECIFIC SINTAX
    public StringBuilder verifiedInput(String [] estend){
        StringBuilder getting = new StringBuilder();
        for( int i = 0; i < estend.length - 1; i++){
            getting.append(estend[i] + " ");
        }
        return getting;
    }
}

