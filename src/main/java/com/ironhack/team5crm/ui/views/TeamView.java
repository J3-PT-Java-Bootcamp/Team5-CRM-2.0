//package com.ironhack.team5crm.ui.views;
//
//
//import com.ironhack.team5crm.models.enums.Industry;
//import com.ironhack.team5crm.services.OpportunityServiceImple;
//import com.ironhack.team5crm.ui.exceptions.AbortedException;
//import com.ironhack.team5crm.ui.exceptions.WrongInputException;
//import com.ironhack.team5crm.ui.panes.TeamPane;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//
//public class TeamView {
//    @Autowired
//    static OpportunityServiceImple opportunityServiceImple;
//
//
//    static TeamPane teamPane = new TeamPane();
//
//    public static void main() throws AbortedException {
//        String selection1 = getFirstSelection();
//        String selection2 = getFirstSelection();
//
//        teamPane.showMessageDialog("Result", opportunityServiceImple.statesByOpportunity(selection2, selection1),1);
//
//    }
//    public static String getFirstSelection() throws AbortedException {
//
//        String selection;
//
//        String[] options = {
//                Report.MIN,
//                Report.MAX,
//                Report.MEDIAN,
//                Report.MEAN,
//        };
//
//        // this is the message displayed on the window with the dropdown menu
//        String message =
//                "<html>" +
//                        "<h2>List of commands</h2></br>" +
//                        "<p>  </p></br>" +
//
//                        "<p>Select [MEAN] to see the mean number of Opportunities associated with an Account. <p>" +
//                        "<p>Select [MEDIAN] to see the median number of Opportunities associated with an Account. <p>" +
//                        "<p>Select [MAX] to see the maximum number of Opportunities associated with an Account. <p>" +
//                        "<p>Select [MIN to see the minimum number of Opportunities associated with an Account. <p>" +
//
//                        "<p>  </p></br>" +
//                        "<p>  </p></br>" +
//                        "</html>";
//
//        // opens a dropdown menu
//        selection = (String) teamPane.showInputDialog(
//                "Report by ",
//                message,
//                JOptionPane.QUESTION_MESSAGE,
//                options,
//                "select");
//
//        return selection;
//    }
//
//    public static String getSecondSelection() throws AbortedException {
//
//        String selection;
//
//        String[] options = {
//                Report.MIN,
//                Report.MAX,
//                Report.MEDIAN,
//                Report.MEAN,
//        };
//
//        // this is the message displayed on the window with the dropdown menu
//        String message =
//                "<html>" +
//                        "<h2>Select your Category</h2></br>" +
//                        "<p>  </p></br>" +
//
//                        "<p>By Opportunities<p>" +
//                        "<p>By Employee Count<p>" +
//                        "<p>By Product<p>" +
//                        "<p>By Industry<p>" +
//                        "<p>By Country<p>" +
//                        "<p>By City<p>" +
//
//                        "<p>  </p></br>" +
//                        "<p>  </p></br>" +
//                        "</html>";
//
//        // opens a dropdown menu
//        selection = (String) teamPane.showInputDialog(
//                "Report by ",
//                message,
//                JOptionPane.QUESTION_MESSAGE,
//                options,
//                "select");
//        switch (selection) {
//            case Report.OPPS, Report.EMPLOYEE_COUNT, Report.PRODUCTS, Report.INDUSTRIES, Report.COUNTRIES, Report.CITIES -> {
//                teamPane.showMessageDialog("Eureka", "You selected " + selection, 1);
//            }
//            default -> throw new AbortedException();
//        }
//        return selection;
//    }
//}
