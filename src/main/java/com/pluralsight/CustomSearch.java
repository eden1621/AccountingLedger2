package com.pluralsight;

import static com.pluralsight.AccountingLedgerApp.*;
public class CustomSearch {
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//


    public static void searchDescription() {
        Ledger.getTransaction();
        System.out.println("Enter Description : ");
        String searchDescription = myScanner.nextLine().trim();
        for (Transaction t : allTransaction) {
            if (t.getVendor().equalsIgnoreCase(searchDescription)) {
                System.out.println(t);
            }
        }
    }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Custom Search @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
}
