package com.ironhack.team5crm.domain;

import com.ironhack.team5crm.domain.enums.Product;
import com.ironhack.team5crm.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opportunities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //* CONSTRUCTORS
    //**********************************************

    public Opportunity(int id, Contact decisionMaker, Status status, Product product, int quantity) {
        this.id = id;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
    }

    public Opportunity(Status status, Product product, int quantity, Contact decisionMaker, Account account) {
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
    }

    //* Equals, hashcode and toString
    //**********************************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "📘 Opportunity with ID "+ id + ":\n" +
                "-------------------------------:\n" +
                "🗣 Decision Maker is " + decisionMaker.getName() +" (Contact ID: "+decisionMaker.getId() + ")\n" +
                "📞: "+decisionMaker.getPhoneNumber()+ "  |  📧: " +decisionMaker.getEmail()+ "\n" +
                "PRODUCT: "+ product +" | QUANTITY: " + quantity + " | STATUS: " + status + "\n";
    }
}
