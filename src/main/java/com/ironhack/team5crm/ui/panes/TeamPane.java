package com.ironhack.team5crm.ui.panes;

import com.ironhack.team5crm.services.AccountServiceImple;
import com.ironhack.team5crm.services.LeadServiceImple;
import com.ironhack.team5crm.services.OpportunityServiceImple;
import com.ironhack.team5crm.services.SalesRepServiceImpl;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class TeamPane extends JOptionPane {

    ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");


    public void showMessageDialog(String title,Object message,
                                         int messageType) throws HeadlessException {

        showOptionDialog(null,
                message,
                title,
                DEFAULT_OPTION,
                messageType,
                teamIcon,
                null,
                null);
    }

    public void showNotFoundDialog(TeamPaneDialog dialog) throws HeadlessException {

        String message;
        String title = "Not Found";
        int messageType = 2;

        switch (dialog){
            case DATA_NOT_FOUND -> {
                message = "Data not found.";
            }
            case COMMAND_NOT_RECOGNIZED -> {
                message = "Command not recognized, please try again.";
                title = "Not Recognized";
            }
            case ACCOUNTS_NOT_FOUND -> {
                message = "No Accounts in the Database!";
            }
            case LEADS_NOT_FOUND -> {
                message = "No Leads in the Database!";
            }
            case OPPORTUNITIES_NOT_FOUND -> {
                message = "No Opportunities in the Database!";
            }
            case SALESREPS_NOT_FOUND -> {
                message = "No Sales Representatives in the Database!";
            }

            default -> {
                message = "Something went wrong";
                title = "Warning";
            }
        }

        showOptionDialog(null,
                message,
                title,
                DEFAULT_OPTION,
                messageType,
                teamIcon,
                null,
                null);
    }

    public void showOppStatusUpdate(TeamPaneDialog dialog) throws HeadlessException {

        String message;
        String title = "Status Updated";;
        int messageType = 1;

        switch (dialog){
            case OPP_STATUS_OPEN -> {
                message ="✏️ Opportunity Status is now 'OPEN': \n";
            }
            case OPP_STATUS_CLOSED_LOST -> {
                message ="🆑 Opportunity Status is now 'CLOSE_LOST': \n";
            }
            case OPP_STATUS_CLOSED_WON -> {
                message ="✅ Opportunity Status is now 'CLOSE_WON': \n";
            }
            default -> {
                message = "Something went wrong";
                title = "Warning";
                messageType = 2;
            }
        }
        showOptionDialog(null,
                message,
                title,
                DEFAULT_OPTION,
                messageType,
                teamIcon,
                null,
                null);
    }

    public Object showInputDialog(String title, Object message, int messageType,
                                         Object[] selectionValues, Object initialSelectionValue)  throws HeadlessException {

        return showInputDialog(null,
                message,
                title,
                messageType,
                teamIcon,
                selectionValues,
                initialSelectionValue);
    }

    public Object showInputDialog(String title, Object message, int messageType)  throws HeadlessException {

        return showInputDialog(null,
                message,
                title,
                messageType,
                teamIcon,
                null,
                null);
    }


    public void showScrollPaneFor (TeamScrollPaneContent contentType, List contentList) throws EmptyException {
        String typeOfContent = "";
        String title = "";



        // Generates the initial String for the Text Area
        StringBuffer output =  new StringBuffer();
        output.append("Following " + typeOfContent + " where found in the database: \n");
        output.append("**************************************************************\n\n");

        // Switch logic
        switch (contentType){
            case LEADS -> {
                typeOfContent = "Leads";
                title = "Leads in Database";

            }
            case ACCOUNTS -> {
                typeOfContent = "Accounts";
                title = "Accounts in Database";

            }
            case OPPORTUNITIES -> {
                typeOfContent = "Opportunities";
                title = "Opportunities in Database";
            }
            case SALESREPS -> {
                typeOfContent = "Sales Representatives";
                title = "Sales Representatives in Database";
            }

            default -> throw new EmptyException("Unexpected value: " + contentType);
        }

        // add the requested information to the string
        for (var content : contentList) {
            output.append(content.toString()).append("\n");
        }

        // Generates and sets the text area with the string before created
        JTextArea textArea = new JTextArea(String.valueOf(output));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //put the text area in the scroll pane and set the scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        //
        showMessageDialog(title, scrollPane,1);
    }

    public int showConfirmDialog(String title, String message, int optionType, int messageType) {
        return showConfirmDialog(null,message,title,optionType, messageType,teamIcon);
    }

    public int showConfirmDialog(String title, String message, int optionType) {
        return showConfirmDialog(null,message,title,optionType, QUESTION_MESSAGE ,teamIcon);
    }
}
