package com.ironhack.team5crm.ui.views;

public interface Operations {

    String NEW_SALES_REP = "new salesrep ";
    String ALL_SALES_REP = "show salesrep ";

    //  by report SalesRep
    String LEAD_REP = "report lead by salesrep";
    String OPP_REP = "report opportunity by salesrep";
    String CLOSE_REP = "report closed-won by salesrep";
    String LOST_REP = "report closed-lost by salesrep";
    String OPEN_REP = "report open by salesrep";

    //  by report PRODUCT
    String OPPORTUNITY = "report opportunity by ";
    String CLOSE_WON = "report closed-won by ";
    String CLOSE_LOST = "report closed-lost by ";

    String OPEN = "report open by ";

    String MEAN = "mean ";
    String MEDIAN = "median ";
    String MIN = "min ";
    String MAX = "max ";

    String MEAN_OPPS = "mean opps per account ";

    String MEDIAN_OPPS = "median opps per account ";

    String MIN_OPPS = "min opps per account ";

    String MAX_OPPS = "max opps per account ";
}
