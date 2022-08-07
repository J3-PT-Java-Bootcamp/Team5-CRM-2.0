package com.ironhack.team5crm.domain;

import java.util.Objects;

public class Lead {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;


    //* CONSTRUCTORS
    //**********************************************
    public Lead() {
    }

    public Lead(int id, String name, String phoneNumber, String email, String companyName) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }


    //* GETTERS AND SETTERS
    //**********************************************
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    //* Equals, hashcode and toString
    //**********************************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return id == lead.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "🔹 Lead with ID "+ id + ": \n" +
                "Name: " + name + " | " +
                "Phone Number: " + phoneNumber + " | " +
                "e-mail: " + email + " | " +
                "Company Name: " + companyName + "\n";
    }
}
