package com.ironhack.team5crm.views;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class RegisterFrame extends JFrame implements ActionListener {

    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");
    private LoginFrame loginFrame;
    //JAVAX ATTRIBUTES
    private JLabel labelTitle, presentation;
    private JButton create, exit;


    public RegisterFrame(){

        //**** JPANE : PART TEXT LABEL
        labelTitle = new JLabel("Administrator");
        labelTitle.setBounds(150, 20, 380, 30);
        labelTitle.setFont(new Font("Courier", Font.BOLD, 25));

        presentation = new JLabel();
        presentation.setText("<html>Welcome To Team5's CRM initial setup</br>" +
                "                We hope you are having a nice day!<br>" +
                "<br>" +
                "                Before you can start to work on your leads and opportunities<br>" +
                "                we need to create a SalesRep<br>" +
                "<br>" +
                "                Thanks!</html>");
        presentation.setBounds(70,5,300,330);
        presentation.setFont(new Font("Courier", Font.PLAIN, 16));

        //**** JPANE : PART buttons

        create = new JButton("Create");
        exit = new JButton("Exit");

        create.setBounds(125, 280, 80, 25);
        exit.setBounds(230, 280, 80, 25);

        //**** JPANE : PART, CALL THE LISTENERS
        create.addActionListener(this);
        exit.addActionListener(this);


        //**** JPANE : PART ADDING OBJECTS TO PANEL

        getContentPane().add(labelTitle);
        getContentPane().add(presentation);
        getContentPane().add(create);
        getContentPane().add(exit);


        //**** JPANE : PART CUSTOM SIZE

        setSize(450, 370);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
    }

    public void setdirector(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()){
            case "exit" -> this.dispose();
            case "create" -> loginFrame.setVisible(true);
        }

        dispose();
    }

    //*************** Alejandro view

    /*public void main() throws AbortedException, WrongInputException {


        //if (salesRepServiceImple.findAllSalesRep() == null) {
        JOptionPane.showMessageDialog(null, """
                Welcome To Team5's CRM initial setup
                We hope you are having a nice day!

                Before you can start to work on your leads and opportunities
                we need to create a SalesRep

                Thanks!
                """);
            ///menu.newSalesRep();
            //main();
        //} else {

            //salesRep = null;

            do {
                try {
                    salesRep = loginSalesRep();
                } catch (DataNotFoundException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "SalesRep not found");
                }
            } while (salesRep == null);

            menu.main(salesRep);
        }

        //}

    private SalesRep loginSalesRep() throws DataNotFoundException {
        var salesRepID = JOptionPane.showInputDialog(null, """
                Welcome To Team5's CRM
                We hope you are having a nice day!

                Before you can start to work on your leads and opportunities
                we must ask you to log in with your SalesRep id.

                Thanks!

                """, "Team5's CRM", JOptionPane.QUESTION_MESSAGE, teamIcon, null,
                null);

        return salesRepServiceImple.findSalesRepById(Integer.parseInt(salesRepID.toString()));
    }*/

}