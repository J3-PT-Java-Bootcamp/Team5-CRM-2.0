package com.ironhack.team5crm.ui;

public interface ConsoleOperations {

    // Operations to create or find leads / opportunities
    String NEW = "new";
    String CONVERT = "convert";
    String SHOW = "show";
    String LOOKUP = "lookup";

    // Operations for Statuses
    String OPEN = "open";
    String CLOSE_LOST = "close-lost";
    String CLOSE_WON = "close-won";

    // Operations for Products
    String HYBRID = "Hybrid";
    String FLATBED = "Flatbed";
    String BOX = "Box";

    // Operations for Industries
    String PRODUCE = "Produce";
    String ECOMMERCE = "E-Commerce";
    String MANUFACTURING = "Manufacturing";
    String MEDICAL = "Medical";
    String OTHER = "Other Industries";

    // TABLES ENTITYS

    String CLOSE_LOST_TABLES = "close_lost";
    String CLOSE_WON_TABLES = "close_won";

}
