package com.ironhack.team5crm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.ironhack.team5crm.models.enums.Product;
import com.ironhack.team5crm.models.enums.Status;

import java.util.Objects;

@Entity
@Table(name = "opportunities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "decision_maker_id")
    private Contact decisionMaker;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    // * CONSTRUCTORS
    // **********************************************
    public Opportunity(Status status, Product product, int quantity, Contact decisionMaker, Account account, SalesRep salesRep) {
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.salesRep = salesRep;
    }

    // * Equals, hashcode and toString
    // **********************************************
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Opportunity that = (Opportunity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "📘 Opportunity with ID " + id + ":\n" +
                "-------------------------------:\n" +
                "🗣 Decision Maker is " + decisionMaker.getName() + " (Contact ID: " + decisionMaker.getId() + ")\n" +
                "📞: " + decisionMaker.getPhoneNumber() + "  |  📧: " + decisionMaker.getEmail() + "\n" +
                "PRODUCT: " + product + " | QUANTITY: " + quantity + " | STATUS: " + status + "\n" +
                "Sales Rep: " + salesRep.getName() + "\n";
    }
}
