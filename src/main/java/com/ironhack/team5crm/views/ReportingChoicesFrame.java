package com.ironhack.team5crm.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ReportingChoicesFrame extends JFrame implements ActionListener {

    // Autowired to frames
    @Autowired
    SalesRepReportView salesRepReportView;

    @Autowired
    private ProductReportView productReportView;

    @Autowired
    CityReportView cityReportView;

    @Autowired
    CountryReportView countryReportView;

    @Autowired
    IndustryFrameView industryFrameView;

    @Autowired
    EmployeeCountView employeeCountView;

    @Autowired
    QuantityReportView quantityReportView;

    @Autowired
    AccountByOppsReportView accountByOppsReportView;

    //*********************** Attributes by frame
    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");
    private JLabel text, title, image;
    private JComboBox options;
    private JButton exit, select;
    private String [] values;


    public  ReportingChoicesFrame(){

        //**** JPANE : PART TEXT LABEL
        title = new JLabel();
        title.setText("Reporting Options");
        title.setBounds(85, 140, 400, 30);
        title.setFont(new Font("Courier New", 1, 25));
        title.setForeground(Color.gray);

        //ADD THE ICON
        image = new JLabel();
        image.setBounds(295, 15, 130, 130);

        text = new JLabel();
        text.setText("Select any option");
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setBounds(110, 200, 150, 30);

        values = new String []{"SalesRep", "Product", "Country", "City", "Industry", "EmployeeCount", "Quantity", "Opportunity"};
        options = new JComboBox<>(values);
        options.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setForeground(Color.gray);
        options.setBounds(240, 205, 100, 20);


        //**** JPANE : PART BUTTONS
        exit = new JButton();
        exit.setText("Back");
        exit.setBounds(120, 260, 90, 30);
        exit.setFont(new Font("Courier New", Font.PLAIN, 14));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.gray);
        exit.setBorder(BorderFactory.createEtchedBorder());

        select = new JButton();
        select.setText("Select");
        select.setBounds(230, 260, 90, 30);
        select.setFont(new Font("Courier New", Font.PLAIN, 14));
        select.setForeground(Color.WHITE);
        select.setBackground(Color.gray);
        select.setBorder(BorderFactory.createEtchedBorder());

        //**** JPANE : ADD THE LISTENERSv
        exit.addActionListener(this);
        select.addActionListener(this);

        //*** JPANE : PART ADDING OBJECTS TO PANEL

        getContentPane().add(title);
        getContentPane().add(image);
        getContentPane().add(text);
        getContentPane().add(options);
        getContentPane().add(exit);
        getContentPane().add(select);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("exit") ){
            dispose();
        } else if (e.getActionCommand().equalsIgnoreCase("select")) {
            var aux = options.getSelectedItem().toString();
            switch (aux.toLowerCase()){
                case "salesrep" -> salesRepReportView.setVisible(true);
                case "product" -> productReportView.setVisible(true);
                case "country" -> countryReportView.setVisible(true);
                case "city" -> cityReportView.setVisible(true);
                case "industry" -> industryFrameView.setVisible(true);
                case "employeecount" -> employeeCountView.setVisible(true);
                case "quantity" -> quantityReportView.setVisible(true);
                case "opportunity" -> accountByOppsReportView.setVisible(true);

            }
            setVisible(false);
        }
        dispose();
    }
}

