package com.ironhack.team5crm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ironhack.team5crm.models.enums.Industry;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "industry")
    private Industry industry;

    @Column(name = "employee_count")
    private int employeesCount;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Contact> contactList;

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Opportunity> opportunityList;

    // * CONSTRUCTOR
    // **********************************************
    public Account(Industry industry, int employeesCount, String city, String country, List<Contact> contactList,
            List<Opportunity> opportunityList) {
        this.industry = industry;
        this.employeesCount = employeesCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    // * Equals, hashcode and toString
    // **********************************************
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        Account account = (Account) object;
        return id == account.id;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {

        // Creating String with all contacts
        String contactsString = "";
        for (Contact contactInList : contactList) {
            contactsString = contactsString + contactInList.toString() + "\n";
        }

        // Creating String with all Opportunities
        String opportunitesString = "";
        for (Opportunity oppInList : opportunityList) {
            opportunitesString = opportunitesString + oppInList.toString() + "\n";
        }

        // adding the two strings creaated above (contacts and opportunities) and
        // addinf this to the toString final string.
        return "📁 Account with ID " + id + ": \n" + "***************************\n" + "Industry: " + industry
                + " | Number of Employees: " + employeesCount + "\n" + "City / Country: " + city + " - " + country
                + "\n\n" + "👥 Contacts in this account \n" + "---------------------------\n" + contactsString
                + "📖 Opportunites in this account \n" + "---------------------------\n" + opportunitesString + "\n";
    }
}
