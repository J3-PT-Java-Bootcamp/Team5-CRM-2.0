package com.ironhack.team5crm.ui;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.SalesRepServiceImple;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import com.ironhack.team5crm.ui.panes.TeamPane;
import com.ironhack.team5crm.views.PrincipalView;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@NoArgsConstructor
public class Login {

    TeamPane teamPane = new TeamPane();

    @Autowired
    SalesRepServiceImple salesRepServiceImple;

    @Autowired
    Menu menu;

    @Autowired
    PrincipalView startView;

    String welcomeMessage =
            """
            Welcome To Team5's CRM initial setup
            We hope you are having a nice day!

            Before you can start to work on your leads and opportunities
            we need to create a SalesRep

            Thanks!
            """;


    public void main() throws WrongInputException {


        if (salesRepServiceImple.getAll().isEmpty()) {

            teamPane.showMessageDialog("welcome", welcomeMessage,JOptionPane.INFORMATION_MESSAGE);
            menu.newSalesRep();
            //main();
            //**new view

        } else {

            SalesRep salesRepLoggedIn = null;

            do {
                try {
                    salesRepLoggedIn = loginSalesRep();
                } catch (DataNotFoundException | NumberFormatException e) {
                    teamPane.showMessageDialog("Not Found", "SalesRep not found",JOptionPane.ERROR_MESSAGE);
                }
            } while (salesRepLoggedIn == null);

            //menu.main(salesRepLoggedIn);
            //****new method
            startView.setDirector(salesRepLoggedIn);
            startView.setVisible(true);
        }

    }

    private SalesRep loginSalesRep() throws DataNotFoundException, NumberFormatException {
        var salesRepID = teamPane.showInputDialog(
                                        "Team5's CRM",
                                        welcomeMessage,
                                        JOptionPane.QUESTION_MESSAGE);

        return salesRepServiceImple.findSalesRepById(Integer.parseInt(salesRepID.toString()));
    }


    //new method for send the SalesRep

}
