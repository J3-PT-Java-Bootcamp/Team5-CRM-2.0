Introduction

You will continue to work on your CRM application. If your team was unable to complete the CRM application from Unit 2, you may request the solution from your instructor to support you in completing this unit’s homework.

Additionally, please create a NEW repository, copy your previous homework, and work in this new repository! This is so that your previous homework can continue to be peer-reviewed.

The primary goal in this unit is to add persistent data storage and to build a reporting feature.

To support the reporting feature, you will need to build one additional class called SalesRep. Each SalesRep should have a name and id. Each Lead and Opportunity should have an associated SalesRep.

Let’s walk through an actual use-case of the reporting feature:

    Luz is the Director of Sales at LBL Trucking Company. Luz is in trouble. Sales have not been as good as the company projected in January and the board of directors has given them 1 quarter to increase sales by 10%.

    Luz needs to quickly determine the most important factors in acquiring Leads, converting a Lead to an Opportunity, and converting an OPEN Opportunity to a CLOSED_WON Opportunity.

    All of this information can be found in the reporting feature.

    First, she types “Report Lead by SalesRep”. The CRM displays the total number of Leads owned by each SalesRep:

James 142
Sara 153
Michael 126

Julia 94

    Next, Luz types “Report Opportunity by SalesRep”. The CRM displays the total number of Opportunities owned by each SalesRep, regardless of their status:

James 103
Sara 112
Michael 107

Julia 92

    Lastly, Luz types “Report CLOSED-WON by SalesRep”. The CRM displays the total number of Opportunities owned by each SalesRep with a CLOSED_WON status:

James 13
Sara 17
Michael 7

Julia 50

    It appears that Julia acquires fewer Leads, but has the highest conversion rate by a large margin. Luz asks Julia to train the entire Sales division on how to close sales.

    Sales immediately go up by 2%. It’s a large improvement, but not significant enough. Fortunately, the reporting feature allows Luz to find Leads, Opportunities, and Opportunities by status not only by SalesRep but also by product, country, city, and industry.

    Luz will continue to pull reports and find contributing factors to sales to create a solid sales improvement plan.



Requirements

For this project you must accomplish all of the following:

Core Functionality

    Create a new use case diagram and add it to your README.md.
    Create a new class diagram and add it to your README.md.
    Create unit tests for every method other than basic getters, setters, and constructors (getters and setters with logic do require unit tests).
    Handle all exceptions gracefully (incorrect input should not crash the program).
    All data should be stored in a normalized SQL database.
    SalesReps can be added to the CRM by typing the command “New SalesRep” (case insensitive).
    When a new SalesRep is created the user will be prompted for a name. A name must be supplied to create the new SalesRep.
    The system should automatically create an id for the SalesRep.
    A list of all SalesReps can be displayed by typing “Show SalesReps” (case insensitive).
    When a new Lead is created, the user should be prompted for a SalesRep id in addition to the prompts created in the previous assignment. The new Lead should be associated with a SalesRep by the provided id.
    When a Lead is converted to an Opportunity, the Opportunity should automatically be associated with the same SalesRep as the Lead.
    Review requirements 12 and 13 from the previous assignment. Note that when a Lead was converted an Opportunity was created, after which users were prompted for Account information to create a new Account. Between these steps, users should be prompted to select whether they would like to create a new Account.

Would you like to create a new Account? (Y/N)

    If the user types Y, the program should continue as it previously did by prompting the user for Account information.
    If the user types N, the program should prompt the user for an Account id and associate the Contact and Opportunity with the specified Account. (You must handle all probable occurrences such as a user inputting an id that doesn’t exist).


Reporting Functionality

    By SalesRep


    A count of Leads by SalesRep can be displayed by typing “Report Lead by SalesRep”
    A count of all Opportunities by SalesRep can be displayed by typing “Report Opportunity by SalesRep”
    A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing “Report CLOSED-WON by SalesRep”
    A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing “Report CLOSED-LOST by SalesRep”
    A count of all OPEN Opportunities by SalesRep can be displayed by typing “Report OPEN by SalesRep”


    By Product


    A count of all Opportunities by the product can be displayed by typing “Report Opportunity by the product”
    A count of all CLOSED_WON Opportunities by the product can be displayed by typing “Report CLOSED-WON by the product”
    A count of all CLOSED_LOST Opportunities by the product can be displayed by typing “Report CLOSED-LOST by the product”
    A count of all OPEN Opportunities by the product can be displayed by typing “Report OPEN by the product”


    By Country


    A count of all Opportunities by country can be displayed by typing “Report Opportunity by Country”
    A count of all CLOSED_WON Opportunities by country can be displayed by typing “Report CLOSED-WON by Country”
    A count of all CLOSED_LOST Opportunities by country can be displayed by typing “Report CLOSED-LOST by Country”
    A count of all OPEN Opportunities by country can be displayed by typing “Report OPEN by Country”


    By City


    A count of all Opportunities by the city can be displayed by typing “Report Opportunity by City”
    A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by City”
    A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by City”
    A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by City”


    By Industry


    A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
    A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
    A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
    A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”


    EmployeeCount States


    The mean employeeCount can be displayed by typing “Mean EmployeeCount”
    The median employeeCount can be displayed by typing “Median EmployeeCount”
    The maximum employeeCount can be displayed by typing “Max EmployeeCount”
    The minimum employeeCount can be displayed by typing “Min EmployeeCount”


    Quantity States


    The mean quantity of products order can be displayed by typing “Mean Quantity”
    The median quantity of products order can be displayed by typing “Median Quantity”
    The maximum quantity of products order can be displayed by typing “Max Quantity”
    The minimum quantity of products order can be displayed by typing “Min Quantity”


    Opportunity States


    The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”



Important Note

Everyone in the squad should contribute equally to the project in both time and lines of code written.

All code must be reviewed before it is merged into the master branch.

All squad members must participate in code review.

Code should not be merged into master if it lacks sufficient test coverage.

This is intended to be a challenging assignment. You will have to rely heavily on your teammates and independent research. Learning independently is a hallmark of a good developer and our job is to turn you into good developers. This process may be frustrating but you will learn a ton!
