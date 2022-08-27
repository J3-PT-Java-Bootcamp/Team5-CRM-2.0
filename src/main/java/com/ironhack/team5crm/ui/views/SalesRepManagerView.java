package com.ironhack.team5crm.ui.views;

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
public class SalesRepManagerView extends JFrame implements ActionListener, Operations {

    @Autowired
    SalesRepServiceImple salesRepServiceImple;
    @Autowired
    AdminSection adminSection;
    @Autowired
    private Menu menu;

    static JPanel panel = new JPanel();
    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");
    private JLabel text, title, image;
    private JTextField textField;
    private JButton search, exit;

    public SalesRepManagerView() {
//        getContentPane().add(panel);
//        panel.setBackground(Color.white);
//        panel.setLayout(null);
//        panel.setBackground(Color.WHITE);
//        panel.setSize(450, 350);
        

        //SET DEFAULT VIEW-FONT
        String viewFont = "melo";

        
        //**** JPANE : PART TEXT LABEL
        title = new JLabel("<html><p>MANAGE SALES REPS.</p></br> </html>");
        title.setBounds(80, 50, 400, 30);
        title.setFont(new Font(viewFont, 1, 25));
        title.setForeground(Color.black);

        //ADD THE ICON
        image = new JLabel(teamIcon);
        image.setBounds(500, 15, 130, 130);

        text = new JLabel();
        text.setText(
                "<html><h2>List of commands</h2></br>" +
                        "<p>  </p></br>"+
                        "<p><b>[ new salesrep ]</b> -> creates a new sales rep. <p>" +
                        "<p><b>[ show salesrep ]</b> -> show all sales rep. <p>"+
                        "<p>  </p></br>"+
                        "<p>Please, select an option to continue.</p>"+
                "</html>"
        );
        text.setBounds(80, 110, 350, 200);
        text.setFont(new Font(viewFont, Font.PLAIN, 14));
        text.setForeground(Color.darkGray);


        //**** JPANE : PART TEXTFIELD
        textField = new JTextField();
        textField.setText("");
        textField.setBounds(200, 300, 270, 30);
        textField.setFont(new Font(viewFont, Font.BOLD, 15));
        textField.setBackground(Color.white);

        //**** JPANE : PART BUTTONS
        exit = new JButton();
        exit.setText("Back");
        exit.setBounds(250, 340, 80, 30);
        exit.setFont(new Font(viewFont, Font.PLAIN, 14));
        exit.setForeground(Color.darkGray);
        exit.setBackground(Color.blue);
        exit.setBorder(BorderFactory.createEtchedBorder());

        search = new JButton();
        search.setText("Search");
        search.setBounds(340, 340, 80, 30);
        search.setFont(new Font(viewFont, Font.PLAIN, 14));
        search.setForeground(Color.darkGray);
        search.setBackground(Color.blue);
        search.setBorder(BorderFactory.createEtchedBorder());

        //**** JPANE : ADD THE LISTENERS
        search.addActionListener(this);
        exit.addActionListener(this);

        //*** JPANE : PART ADDING OBJECTS TO PANEL

        getContentPane().add(title);
        getContentPane().add(image);
        getContentPane().add(text);
        getContentPane().add(textField);
        getContentPane().add(search);
        getContentPane().add(exit);

        //CUSTOM UI VALUES TO FRAME
        getContentPane().setBackground(Color.WHITE);


        //**** JPANE : SETTING THE SIZE
        //CALL TO CLEANUP METHOD
        setSize(700, 500);
        setTitle("5to3 - CRM");
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
            case "search" -> {
                try {
                    checkTheText(textField.getText());
                } catch (EmptyException | WrongInputException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        setVisible(false);
        adminSection.setVisible(true);
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
