package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionClass {
//attributes/feature what our application should have.
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

//Constructor: for setting the requirement of our app / initializing the values.

    public TransactionClass(LocalDate date, LocalTime time,String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

//Method if we need



//getter and setter for accessing the attributes

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
