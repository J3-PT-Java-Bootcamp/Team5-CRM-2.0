package com.ironhack.team5crm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales_reps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    public SalesRep(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "🧑🏻‍💼 SalesRep with ID " + id + ": \n" +
              "Name: " + name + "\n";
    }

}
