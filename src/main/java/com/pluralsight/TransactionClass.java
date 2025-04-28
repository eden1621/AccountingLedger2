package com.pluralsight;

import java.time.LocalDate;

public class TransactionClass {
//attributes/feature what our application should have.
    private String date;
    private String time;
    private String description;
    private String vendor;
    private String amount;

//Constructor: for setting the requirement of our app / initializing the values.

    public TransactionClass(String date, String time,String description, String vendor, String amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

//Method we need



//getter and setter for accessing the attributes
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
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

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
