package com.pluralsight;

import java.time.LocalDate;

public class CustomSearch {

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
public static void customScreen() {
    System.out.println("-----------------------------------");
    System.out.println("----------- Custom Search ---------");
    System.out.println("-----------------------------------");

    String startDate = Home.validation("Enter the Start Date (MM/dd/yyyy): \n");
    String endDate = Home.validation("Enter the End Date (MM/dd/yyyy): \n");
    String description = Home.validation("Enter the Description: \n");
    String vendor = Home.validation("Enter the Vendor: \n");
    String amount = Home.validation("Enter the Amount: \n");

    LocalDate beginningDate = LocalDate.parse(startDate);
    LocalDate lastDate = LocalDate.parse(endDate);
    double actualAmount = Double.parseDouble(amount);

    for (Transaction t : AccountingLedgerApp.allTransaction) {
        boolean matches = true;

        if (startDate != null && t.getDate().isBefore(beginningDate)) {
            matches = false;
        }

        if (endDate != null && t.getDate().isAfter(lastDate)) {
            matches = false;
        }

        if (!description.isBlank() && !t.getDescription().toLowerCase().contains(description)) {
            matches = false;
        }

        if (!vendor.isBlank() && !t.getVendor().toLowerCase().contains(vendor)) {
            matches = false;
        }
        if (amount != null && t.getAmount() != actualAmount) {
            matches = false;
        }

        if (matches) {
            System.out.println(t);
        }

//Work In Progress ....
    }
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
}
