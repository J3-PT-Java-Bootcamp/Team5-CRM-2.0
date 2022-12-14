package com.ironhack.team5crm.ui;

public interface ConsoleOperations {

    // OPERATIONS FOR CREATE OR FIND LEADS / OPPORTUNITIES
    String NEW = "new";
    String CONVERT = "convert";
    String SHOW = "show";
    String LOOKUP = "lookup";

    // OPERATIONS FOR STATUS
    String OPEN = "open";
    String CLOSE_LOST = "close-lost";
    String CLOSE_WON = "close-won";

    //
    String CLOSED_LOST = "closed-lost";
    String CLOSED_WON = "closed-won";

    // OPERATIONS FOR PRODUCTS
    String HYBRID = "Hybrid";
    String FLATBED = "Flatbed";
    String BOX = "Box";

    // OPERATIONS FOR INDUSTRIES
    String PRODUCE = "Produce";
    String ECOMMERCE = "E-Commerce";
    String MANUFACTURING = "Manufacturing";
    String MEDICAL = "Medical";
    String OTHER = "Other Industries";

    // TABLES ENTITIES
    String CLOSE_LOST_TABLES = "closed_lost";
    String CLOSE_WON_TABLES = "closed_won";

    String MEAN = "mean";
    String MEDIAN = "median";
    String MIN = "min";
    String MAX = "max";

    String EMPLOYEE_COUNT = "employeecount";
    String QUANTITY = "quantity";
    String OPPS = "opps";

}
