package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountingLedgerApp {

    //declare the scanner and datetime formatter as a static to be able to use it throughout the class.
    static Scanner myScanner = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy -MM -dd HH:mm:ss");

// static LocalDateTime dateTimeConvert = LocalDateTime.parse( "date time", formatter);

    public static void main(String[] args) {

        //Display the menu page
        homeScreen();
        //take the option choice from the menu store it in variable called userChoice.
        String actualChoice = myScanner.nextLine();
        //If user chooses (D)
        if (actualChoice.equalsIgnoreCase("D")) {
            //ask the deposit information by invoking the addDeposit function.
            addDeposit();
            //If user choose (P)
        } else if (actualChoice.equalsIgnoreCase("P")) {
            System.out.println("not yet done");
        }
        //If user choose (L) show Ledger
        else if (actualChoice.equalsIgnoreCase("L")) {
            System.out.println("not yet done");
        }
        //If user choose (X) exit the application
        else if (actualChoice.equalsIgnoreCase("X")) {
            System.out.println("Goodbye!");
            System.exit(0);  // Properly exit the program
        } else {
            System.out.println("Invalid option. Try again.");
        }
    }
//**************************** HomeScreen Display Method **********************************************//

    //Method to display the menu options
    public static void homeScreen() {

        System.out.println("Welcome to Accounting Ledger App!!!");

        System.out.println("------------------------------");


        System.out.print("What would you like to do?  Select A,B,or C.\n");
        System.out.print("D)Add Deposit\n");
        System.out.print("P)Make Payment(Debit)\n");
        System.out.print("L)Ledger\n");
        System.out.print("X)Exit");
    }

//***************************** End *********************************************************//

//******************************* Add Deposit Method *********************************************************//
    //Method to get deposit information(date,time,description,vendor & amount) and write it to transaction.csv
    public static void addDeposit() {
        try {
            //prompt the user for the deposit information.
            System.out.print("Enter your Deposit information : ");

            System.out.println("------------------------------");

            // Take inputs one by one
            String dateInput = validation("Enter the Date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(dateInput, formatter);

            String timeInput = validation("Enter the Time (HH:mm:ss): ");
            LocalTime time = LocalTime.parse(timeInput, formatter);

            String description = validation("Enter the Description: ");
            String vendor = validation("Enter the Vendor: ");
            String amount = validation("Enter the Amount: ");

            // Instantiate an object from our class transaction
            TransactionClass collectDepositInfo = new TransactionClass(date, time, description, vendor, Double.parseDouble(amount));
            // Write the collected information to a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/transaction.csv",true));
            writer.write(collectDepositInfo.getDate()+ "|" +  collectDepositInfo.getTime() + "|" + collectDepositInfo.getDescription() + "|" + collectDepositInfo.getVendor() + "|" + collectDepositInfo.getAmount());
            writer.newLine();
            writer.close();
            System.out.println("\nDeposit Added to the file Successfully!");

        } catch (Exception e) {
            System.out.println("Error while adding deposit: " + e.getMessage());
        }
    }
//***************************** End *********************************************************//

//****************************************** Validation Method ********************************************************//
    // method to validate if input to the questions is not valid for every question on the addDeposit method.
    public static String validation(String ask) // return type string and parameter is the question I am asking at the addDeposit method.
    {
        String answer = "";// answer to the questions start with nothing
        while (true) {
            System.out.print(ask);//ask question to the user
            answer = myScanner.nextLine().trim();//take the input from the users about the deposit information store it a variable.
            if (answer.isEmpty()) {
                //if the users pass empty input this will be display and continue asking the question.
                System.out.println("Input cannot be empty. Please enter a valid response.");
                continue;
            } else {
                break;
            }
        }
        return answer;// it will return me the answer to the question after validating .
    }
//***************************** End *********************************************************//
    //Method for making a payment(Debit)

}




