package com.pluralsight;

import java.time.LocalDate;

public class TransactionClass {
//attributes/feature what our application should have.
    private String date;
    private String time;
    private String vendor;
    private String amount;

//Constructor: for setting the requirement of our app / initializing the values.

    public TransactionClass(String date, String time, String vendor, String amount) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.amount = amount;
    }

//Method we need



//getter and setter for accessing the attributes
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
