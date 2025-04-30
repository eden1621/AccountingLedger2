package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
//attributes/feature what our application should have.
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendor;
    private final double amount;

//Constructor: for setting the requirement of our app / initializing the values.
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
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
    public LocalTime getTime() {
        return this.time;
    }
    public String getDescription() {
        return this.description;
    }
    public String getVendor() {
        return this.vendor;
    }
    public double getAmount() {
        return this.amount;
    }
@Override //got this method from my peers
    public String toString() {
        String type = (amount< 0 ) ? "Payment" : "Deposit";
        return String.format("Date "+ date.toString()+ " | " + "Time "  + time.toString()+" | "+ "Description " + description+ " | "+ "vendor "+ vendor+ " | "+"amount " + amount + type );

    }
}
