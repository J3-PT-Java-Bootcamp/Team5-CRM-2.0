package com.ironhack.team5crm.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ReportingChoicesFrame extends JFrame implements ActionListener {

    @Autowired
    SalesRepReportView salesRepReportView;

    @Autowired
    private ProductReportView productReportView;

    private JLabel text, title;
    private JComboBox options;
    private JButton exit, select;
    private String [] values;


    public  ReportingChoicesFrame(){

        title = new JLabel();
        title.setText("Reporting Options");
        title.setBounds(60, 20, 180, 25);
        title.setFont(new Font("Courier", Font.BOLD, 20));

        text = new JLabel();
        text.setText("Select any option");
        text.setBounds(30, 70, 150, 20);

        values = new String []{"SalesRep", "Product", "Country", "City", "Industry", "EmployeeCount", "Quantity", "Opportunity"};
        options = new JComboBox<>(values);
        options.setBounds(150, 70, 100, 20);


        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(70, 120, 70, 25);

        select = new JButton();
        select.setText("Select");
        select.setBounds(150, 120, 70, 25);

        exit.addActionListener(this);
        select.addActionListener(this);


        getContentPane().add(title);
        getContentPane().add(text);
        getContentPane().add(options);
        getContentPane().add(exit);
        getContentPane().add(select);


        setSize(300, 220);
        setTitle("From 5 to 3 CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("exit") ){
            dispose();
        } else if (e.getActionCommand().equalsIgnoreCase("select")) {
            var aux = options.getSelectedItem().toString();
            switch (aux.toLowerCase()){
                case "salesrep" -> salesRepReportView.setVisible(true);
                case "product" -> productReportView.setVisible(true);
            }
            dispose();
        }
        dispose();
    }
}

