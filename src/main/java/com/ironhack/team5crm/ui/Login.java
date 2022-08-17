package com.ironhack.team5crm.ui;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.servicesImplements.SalesRepServiceImple;
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
    SalesRepServiceImple salesRepServiceImple;

    @Autowired
    Menu menu;

    public void main() throws AbortedException, WrongInputException {

        if (salesRepServiceImple.findAllSalesRep().isEmpty()) {
            JOptionPane.showMessageDialog(null, """
                    Welcome To Team5's CRM initial setup
                    We hope you are having a nice day!

                    Before you can start to work on your leads and opportunities
                    we need to create a SalesRep

                    Thanks!
                    """);
            //menu.newSalesRep();
            main();
        } else {

            SalesRep salesRepLoggedIn = null;

            do {
                try {
                    salesRepLoggedIn = loginSalesRep();
                } catch (DataNotFoundException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "SalesRep not found");
                }
            } while (salesRepLoggedIn == null);

            menu.main(salesRepLoggedIn);
        }

    }

    private SalesRep loginSalesRep() throws DataNotFoundException, NumberFormatException {
        var salesRepID = JOptionPane.showInputDialog(null, """
                Welcome To Team5's CRM
                We hope you are having a nice day!

                Before you can start to work on your leads and opportunities
                we must ask you to log in with your SalesRep id.

                Thanks!

                """, "Team5's CRM", JOptionPane.QUESTION_MESSAGE, teamIcon, null,
                null);

        return salesRepServiceImple.findSalesRepById(Integer.parseInt(salesRepID.toString()));
    }
}
