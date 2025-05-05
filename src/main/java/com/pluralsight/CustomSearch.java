package com.pluralsight;

import java.time.LocalDate;

import static com.pluralsight.AccountingLedgerApp.allTransaction;
import static com.pluralsight.AccountingLedgerApp.myScanner;

public class CustomSearch {

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
public static void customScreen() {
    //Display message
    System.out.println("-----------------------------------");
    System.out.println("----------- Custom Search ---------");
    System.out.println("-----------------------------------");

    //prompt the user to input search type
    System.out.println("Enter the Start Date (MM/dd/yyyy): \n");
    String startDate = myScanner.nextLine().trim();
    System.out.println("Enter the End Date (MM/dd/yyyy): \n");
    String endDate = myScanner.nextLine().trim();
    System.out.println("Enter the Description: \n");
    String description= myScanner.nextLine().trim();
    System.out.println("Enter the Vendor: \n");
    String vendor = myScanner.nextLine().trim();
    System.out.println("Enter the Amount: \n");
    String amount = myScanner.nextLine().trim();

    //convert input to the appropriate data type if not empty.
    LocalDate beginningDate = startDate.isEmpty() ? null : LocalDate.parse(startDate);
    LocalDate lastDate = endDate.isEmpty() ? null : LocalDate.parse(endDate);
    Double actualAmount = amount.isEmpty() ? null : Double.parseDouble(amount);


    //Based on the input do the logic
    boolean found = false;

    for (Transaction t : AccountingLedgerApp.allTransaction) {

        boolean matches = true;

        // ================= Date Matching ====================
        LocalDate tDate = t.getDate();

        if (beginningDate != null && lastDate != null) {
            if (tDate.isBefore(beginningDate) || tDate.isAfter(lastDate)) {
                matches = false;
            }
        } else if (beginningDate != null) {
            if (tDate.isBefore(beginningDate)) {
                matches = false;
            }
        } else if (lastDate != null) {
            if (tDate.isAfter(lastDate)) {
                matches = false;
            }
        }

        // ================ Description Matching ================
        if (!description.isBlank() && !t.getDescription().toLowerCase().contains(description.toLowerCase())) {
            matches = false;
        }

        // ================ Vendor Matching =====================
        if (!vendor.isBlank() && !t.getVendor().toLowerCase().contains(vendor)) {
            matches = false;
        }

        // ================ Amount Matching =====================
        if (actualAmount != null && t.getAmount() != actualAmount) {
            matches = false;
        }

        if (matches) {
            System.out.println(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions matched your custom search.");
    }
}
}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//

