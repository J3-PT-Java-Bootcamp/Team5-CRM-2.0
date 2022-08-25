package com.ironhack.team5crm.views;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.servicesImplements.SalesRepServiceImple;
import com.ironhack.team5crm.ui.Menu;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginFrame extends JFrame implements ActionListener {

    private SalesRepServiceImple salesRepServiceImple;
    private SalesRep salesRep;
    private AdminFrame adminFrame;
    private JButton register, exit;
    private JLabel name, title;
    private JTextField nameField;


    public LoginFrame(){

        //**** JPANE : PART TEXT LABEL
        title = new JLabel("Register");
        title.setBounds(95, 20, 200, 30);
        title.setFont(new Font("Courier", Font.BOLD, 20));

        name = new JLabel();
        name.setText("Name");
        name.setFont(new Font("Courier", Font.PLAIN, 16));
        name.setBounds(30, 60, 80, 30);

        //**** JPANE : PART TEXT FIELD
        nameField = new JTextField();
        nameField.setText("");
        nameField.setBounds(80, 70, 150, 20);

        //**** JPANE : PART BUTTONS
        register = new JButton();
        register.setText("Register");
        register.setBounds(50, 110, 90, 20);
        exit = new JButton();
        exit.setText("Register");
        exit.setBounds(150, 110, 90, 20);

        //**** JPANE : PART, CALL THE LISTENERS
        register.addActionListener(this);
        exit.addActionListener(this);

        //**** JPANE : ADD OBJECTS
        getContentPane().add(title);
        getContentPane().add(name);
        getContentPane().add(nameField);
        getContentPane().add(register);
        getContentPane().add(exit);

        //**** JPANE : PART, FIXED THE SIZE
        setSize(300, 200);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }

    public void setDirector(AdminFrame adminFrame){
        this.adminFrame = adminFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //call to repos, controllers and services
        switch (e.getActionCommand().toLowerCase()){
            case "exit" -> dispose();
            case "register" -> adminFrame.setVisible(true);
        }
        dispose();
    }
}
