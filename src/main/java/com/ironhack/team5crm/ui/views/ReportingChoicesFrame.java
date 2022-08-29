package com.ironhack.team5crm.ui.views;

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
    AdminSection adminSection;

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
        String viewFont = "melo";


        //**** JPANE : PART TEXT LABEL
        title = new JLabel();
        title.setText("<html><p>Report </p></br> " +
                "<p>Options<p></html>");;
        title.setBounds(66, 40, 350, 100);
        title.setFont(new Font(viewFont, 1, 25));
        title.setForeground(Color.darkGray);

        //ADD THE ICON
        image = new JLabel(teamIcon);
        image.setBounds(280, 15, 150, 150);

        text = new JLabel();
        text.setText("Select any option:");
        text.setFont(new Font(viewFont, Font.PLAIN, 14));
        text.setBounds(66, 200, 150, 30);

        values = new String []{"SalesRep", "Product", "Country", "City", "Industry", "EmployeeCount", "Quantity", "Opportunity"};
        options = new JComboBox<>(values);
        options.setFont(new Font(viewFont, Font.PLAIN, 14));
        options.setBounds(240, 205, 200, 20);


        //**** JPANE : PART BUTTONS
        exit = new JButton();
        exit.setText("Back");
        exit.setBounds(130, 260, 90, 30);
        exit.setFont(new Font(viewFont, Font.PLAIN, 14));
//        exit.setForeground(Color.darkGray);
//        exit.setBackground(Color.blue);
        exit.setBorder(BorderFactory.createEtchedBorder());

        select = new JButton();
        select.setText("Select");
        select.setBounds(250, 260, 90, 30);
        select.setFont(new Font(viewFont, Font.PLAIN, 14));
//        select.setForeground(Color.darkGray);
//        select.setBackground(Color.blue);
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
        setSize(500, 400);
        setTitle("5to3 - CRM");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("back") ){
            adminSection.setVisible(true);
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
        }
        setVisible(false);
    }
}

