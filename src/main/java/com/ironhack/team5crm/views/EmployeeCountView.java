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
public class EmployeeCountView extends JFrame implements ActionListener, Operations {

    @Autowired
    OpportunityServiceImple opportunityServiceImple;

    private JLabel title, text;
    private JTextField field;
    private JButton exit, search;

    public EmployeeCountView(){

        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p style = 'color : red;'>Reports By EmployeeCount<p></html>");
        title.setBounds(200, 20, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p><b>[Mean EmployeeCount]</b> -> The mean employeeCount. <p>" +
                        "<p><b>[Median EmployeeCount]</b> -> The median employeeCount. <p>" +
                        "<p><b>[Max EmployeeCount]</b> -> The maximum employeeCount. <p>" +
                        "<p><b>[Min EmployeeCount]</b> -> The minimum employeeCount. <p></html>"
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
    public void cleanUp(){
        field.setText("");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()){
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
        if(extension.length < 1){
            throw new WrongInputException();
        }

        //CALL TO METHOD FOR CHECK THE SPECIFIC SINTAX
        String toVerified = String.valueOf(verifiedInput(extension));
        String stats = extension[0];
        String employee = extension[1];

        switch (toVerified) {
            case MEAN , MEDIAN, MIN, MAX -> JOptionPane.showMessageDialog(null, opportunityServiceImple.medianByEmployeeCount());
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
