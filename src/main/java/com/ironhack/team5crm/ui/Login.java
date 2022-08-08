package com.ironhack.team5crm.ui;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.SalesRepService;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@NoArgsConstructor
public class Login {
    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    @Autowired
    SalesRepService salesRepService;

    @Autowired
    Menu menu;

    public void main() throws AbortedException, WrongInputException {

        if(salesRepService.findAllSalesRep().isEmpty()) {
            JOptionPane.showMessageDialog(null, """
                    Welcome To Team5's CRM initial setup
                    We hope you are having a nice day!
                                    
                    Before you can start to work on your leads and opportunities
                    we need to create a SalesRep
                                    
                    Thanks!
                    """);
            menu.newSalesRep();
            main();
        }else {

            var salesRepID = JOptionPane.showInputDialog(null, """
                            Welcome To Team5's CRM
                            We hope you are having a nice day!
                                            
                            Before you can start to work on your leads and opportunities
                            we must ask you to log in with your SalesRep id.
                                            
                            Thanks!
                             
                            """, "Team5's CRM", JOptionPane.QUESTION_MESSAGE, teamIcon, null,
                    null);

            try {
                SalesRep salesRepLoggedIn = salesRepService.findSalesRepById(Integer.parseInt(salesRepID.toString()));
                menu.main(salesRepLoggedIn);
            } catch (DataNotFoundException e) {
                JOptionPane.showMessageDialog(null, "SalesRep not found");
            }
        }

    }
}