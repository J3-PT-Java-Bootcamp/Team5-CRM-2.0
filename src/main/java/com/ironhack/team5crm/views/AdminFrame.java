package com.ironhack.team5crm.views;

import com.ironhack.team5crm.services.SalesRepServiceImple;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AdminFrame extends JFrame implements ActionListener, Operations {

    @Autowired
    SalesRepServiceImple salesRepServiceImple;

    @Autowired
    AdminSection adminSection;

    @Autowired
    private Menu menu;

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");
    private JLabel text, title, image;
    private JTextField textField;
    private JButton choice, back;

    public AdminFrame() {
        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p>Options to Admin<p></html>");
        title.setBounds(85, 100, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));
        title.setForeground(Color.gray);

        //ADD THE ICON
        image = new JLabel(teamIcon);
        image.setBounds(295, 15, 130, 130);

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p><b>[new salesrep]</b> -> creates a new sales rep. <p>" +
                        "<p><b>[show salesrep]</b> -> show all sales rep. <p></html>"
        );
        text.setBounds(30, -45, 650, 450);
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setForeground(Color.gray);

        //**** JPANE : PART TEXTFIELD
        textField = new JTextField();
        textField.setText("");
        textField.setBounds(170, 245, 100, 30);//need the clean method

        //**** JPANE : PART BUTTONS
        choice = new JButton();
        choice.setText("Choice"); // we need a better name
        choice.setBounds(120, 285, 80, 30);
        choice.setFont(new Font("Courier New", Font.PLAIN, 12));
        choice.setForeground(Color.WHITE);
        choice.setBackground(Color.gray);
        choice.setBorder(BorderFactory.createEtchedBorder());

        back = new JButton();
        back.setText("Back");
        back.setBounds(240, 285, 80, 30);
        back.setFont(new Font("Courier New", Font.PLAIN, 12));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.gray);
        back.setBorder(BorderFactory.createLoweredSoftBevelBorder());

        //**** JPANE : ADD THE LISTENERS
        choice.addActionListener(this);
        back.addActionListener(this);

        //*** JPANE : PART ADDING OBJECTS TO PANEL

        getContentPane().add(title);
        getContentPane().add(image);
        getContentPane().add(text);
        getContentPane().add(textField);
        getContentPane().add(choice);
        getContentPane().add(back);

        //CUSTOM UI VALUES TO FRAME
        getContentPane().setBackground(Color.WHITE);


        //**** JPANE : SETTING THE SIZE
        //CALL TO CLEANUP METHOD
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
            case "back" -> adminSection.setVisible(true);
            case "choice" -> {
                try {
                    checkTheText(textField.getText());
                } catch (EmptyException | WrongInputException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        setVisible(false);
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

        switch (toVerified) {
            case NEW_SALES_REP -> menu.newSalesRep();
            case ALL_SALES_REP-> menu.showSalesRep();
            default -> JOptionPane.showMessageDialog(null, "only a valid option, check your sintax");
        }
        dispose();
    }

    //METHOD FOR CHECK THE SPECIFIC SINTAX
    public StringBuilder verifiedInput(String [] estend){
        StringBuilder getting = new StringBuilder();
        for( int i = 0; i < estend.length; i++){
            getting.append(estend[i] + " ");
        }
        return getting;
    }

}
