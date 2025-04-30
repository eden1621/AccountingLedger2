package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {

//declare the scanner and datetime formatter as a static to be able to use it throughout the class.
static Scanner myScanner = new Scanner(System.in);
static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
static ArrayList<Transaction> allTransaction = new ArrayList<>();
// static LocalDateTime dateTimeConvert = LocalDateTime.parse( "date time", formatter);

public static void main(String[] args) {

    //Display the menu page
        homeScreen();
}
//**************************** HomeScreen Display Method **********************************************//
// Method to display the menu options
public static void homeScreen() {
    boolean input = true;
    while (input) {
        System.out.println("-----------------------------------");
        System.out.println("Welcome to Accounting Ledger App!!!");
        System.out.println("-----------------------------------");

        System.out.print("What would you like to do?  Select D,P, L or X.\n");
        System.out.print("D)Add Deposit\n");
        System.out.print("P)Make Payment(Debit)\n");
        System.out.print("L)Ledger\n");
        System.out.print("X)Exit\n");

        //take the option choice from the menu store it in variable called userChoice.
        String actualChoice = myScanner.nextLine();
        //If user chooses (D)
        if (actualChoice.equalsIgnoreCase("D")) {
            //ask the deposit information by invoking the addDeposit function.
            addDeposit();
            //If user choose (P)
        } else if (actualChoice.equalsIgnoreCase("P")) {
            addPayment();
        }
        //If user choose (L) show Ledger
        else if (actualChoice.equalsIgnoreCase("L")) {
            //=========================== Ledger Screen =====================
            //Calling this method display the Ledger menu options
            ledgerScreen();
        }
        //If user choose (X) exit the application
        else if (actualChoice.equalsIgnoreCase("X")) {
            System.out.println("Goodbye!");
            input = false;  // Properly exit the program// we can turn the boolean to false
        } else {
            System.out.println("Invalid option. Try again.");
        }
    }
}/* Display welcome message and options on the screen/console*/
//***************************** End *********************************************************//

//******************************* Add Deposit Method ******************************************************//
//Method to get deposit information(date,time,description,vendor & amount) and write it to transaction.csv
public static void addDeposit() {
    //wrap the question with answer return in a while loop because I want to ask each question again if it is not valid.
    try {
        //first create a file and write the header (only if file is new)
        String fileCsv = "src/main/resources/transaction.csv";//declare the file name we want with a path.

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCsv, true));//write true so it doesn't overwrite the file.Keep old records.

        //Set condition to write the header => create only if the file is empty so that it won't repeat.
        File file = new File(fileCsv);// This I got from AI =>this doesn't write, just points to the file

        if (file.length() == 0) {  // checks if file is empty
            writer.write("Date | Time | Description | Vendor | Amount ");
        }
        writer.newLine();//to move to next line after header

        //prompt the user for the deposit information.
        System.out.println("------------------------------");
        System.out.print("Enter your Deposit information : \n");
        System.out.println("------------------------------\n");

        //passing the questions in the method validation that return the answer and validate it and store it a variable that describe it.

        String description = validation("Enter the Description: \n");
        String vendor = validation("Enter the Vendor: \n");
        String amount = validation("Enter the Amount: \n");//this a string so needed to be parsed to double.
        //amount is a double in the Transaction class but when we received it as answer it is a string.
        // The date and time stamp will be added to the file automatically
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0); // remove nanoseconds for cleaner output=>can also pass the formatter (time.format(formatter1)/date.format(formatter2)
        date.format(formatter);
        time.format(formatter1);

        // Write the collected information to a file in a format required
        // Instantiate an object from our class transaction
        Transaction collectDepositInfo = new Transaction(date, time, description, vendor, Double.parseDouble(amount));

        //write the deposit information to the transaction.csv in this format.
        writer.write(collectDepositInfo.getDate() + " | " + collectDepositInfo.getTime() + " | " + collectDepositInfo.getDescription() + " | " + collectDepositInfo.getVendor() + " | " + collectDepositInfo.getAmount());
        writer.newLine();//write on new line=> format when it's written in the file.
        writer.close();//closing the buffer the writer.
        //Display this message if done correctly
        System.out.println("\nDeposit Added to the file Successfully!");

    } catch (Exception e) {
        System.out.println("Error while adding deposit: " + e.getMessage());
    }
    /*Create csv file and activate our File/Buffered writer with the path of our file.
     * Prompt user to answer deposit info using validation method and store it in variable.
     * Get the Date and Time stamp with formatter better than using default
     * Convert the amount to double because it's string and store in variable.
     * Create an object to collect the csv files data  pass argument.
     * we write on the object with the format passed.
     * Then print success message once done  */
}
//***************************** End of addDeposit *********************************************************//

//******************************* Add Payment+ Method *****************************************************//
//Method to get payment information(date,time,description,vendor & amount) and write it to transaction.csv
public static void addPayment() {

    try {//first create a file and write the header (only if file is new)
        String fileCsv = "src/main/resources/transaction.csv";//declare the file name we want with a path.

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCsv, true));//write true so it doesn't overwrite the file.Keep old records.

        //prompt the user for the payment information.
        System.out.println("------------------------------");
        System.out.print("Enter your Debit information : \n");
        System.out.println("------------------------------\n");

        //passing the questions in the method validation that return the answer and validate it and store it a variable that describe it.

        String description = validation("Enter the Description: \n");
        String vendor = validation("Enter the Vendor: \n");
        String amount = validation("Enter the Amount: \n");//this a string so needed to be parsed to double.
        //amount is a double in the Transaction class but when we received it as answer it is a string.
        // The date and time stamp will be added to the file automatically
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0); // remove nanoseconds for cleaner output=>can also pass the formatter (time.format(formatter1)/date.format(formatter2)
        date.format(formatter);
        time.format(formatter1);

        // Write the collected information to a file in a format required
        // Instantiate an object from our class transaction
        double amountDouble = Double.parseDouble(amount);
        amountDouble = -1 * Math.abs(amountDouble);
        Transaction collectDebitInfo = new Transaction(date, time, description, vendor, amountDouble);

        //write the payment information to the transaction.csv in this format.
        writer.write(collectDebitInfo.getDate() + " | " + collectDebitInfo.getTime() + " | " + collectDebitInfo.getDescription() + " | " + collectDebitInfo.getVendor() + " | " + collectDebitInfo.getAmount());
        writer.newLine();//write on new line=> format when it's written in the file.
        writer.close();//closing the buffer the writer.
        //Display this message if done correctly
        System.out.println("\nPayment Added to the file Successfully!");
    } catch (Exception e) {
        System.out.println("Error while adding payment: " + e.getMessage());
    }
    /*Create csv file and activate our File/Buffered writer with the path of our file.
    * Prompt user to answer deposit info using validation method and store it in variable.
    * Get the Date and Time stamp with formatter better than using default
    * Convert the amount to double because it's string and store in variable.
    * Then do Math.abs to get the absolut value and times it with -1 to have negative because we are dealing with payments.
    * Create an object to collect the csv files data  pass argument.
    * we write on the object with the format we want  the information we want to get.
    * Then print success message once done  */
}
//***************************** End of addPayment *********************************************************//

//****************************************** Validation Method ********************************************//
// method to validate if input to the questions is not valid for every question on the addDeposit method.
public static String validation(String ask) {// return type string and parameter is the question I am asking at the addDeposit method.
    String answer = "";// answer to the questions start with nothing
    while (true) {
        System.out.print(ask);//ask question to the user
        answer = myScanner.nextLine().trim();//take the input from the users about the deposit information store it a variable.
        if (answer.isEmpty()) {
            //if the users pass empty input this will be display and continue asking the question.
            System.out.println("Input cannot be empty. Please enter a valid response.");
        } else {
            break;
        }
    }
    return answer;// it will return me the answer to the question after validating .
}
/*method that have a parameter of string.
* Assist on first ask questions and take user input
* Based on the input evaluate the condition and return the answer.*/
//***************************** End of validation *********************************************************//

// ============================ LEDGER SCREEN ============================== //
// Method to display the menu options
public static void ledgerScreen() {
    boolean askAgain = true;
    while (askAgain) {
        System.out.println("-----------------------------------");
        System.out.println("Welcome to The Ledger Screen !!!");
        System.out.println("-----------------------------------");

        System.out.print("What would you like to do?  Select A,D, P,R or H.\n");
        System.out.print("A)All Entries \n");
        System.out.print("D)Deposit Only\n");
        System.out.print("P)Payments Only \n");
        System.out.print("R)Reports\n");
        System.out.print("H)Home-Screen \n");

        //take the option choice from the menu store it in variable called userChoice.
        String actualChoice1 = myScanner.nextLine();
        //If user chooses (A)
        switch (actualChoice1.toUpperCase()) {
            // If user chose "A" or "a"
            case "A":
                //Call method to read and display all transactions (deposits and payments)
                showTransaction();
                break;
            // If user chose "D"
            case "D":// Call method to read and display only deposit (positive amount) transactions
                readDeposit();
                break;
            // If user chose "P"
            case "P":
                // Call method to read and display only payment (negative amount) transactions
                readPayment();
                break;
            // If user chose "R" or "r"
            case "R":
                // Call method to display the Reports menu screen
                reportScreen();
                break;
            // If user chose "H" or "h"
            case "H":
                // Return to the Home screen
                homeScreen();
                break;
            // If user entered any invalid option
            default:
                System.out.println("Invalid option. Try again.");
                break;
        }

    }
}
/* Display on the console the option for LedgerScreen*/
// ============================ End LEDGER SCREEN ============================== //

// ============================ LEDGER All Entries(A) ============================== //
//Method to Read All the entries to show when called in the main method.
public static ArrayList<Transaction> getTransaction() {
//instantiate a new object that is an ArrayList from transaction class.
try {
    //To read from the file with a booster buffer.
    BufferedReader transactionRead = new BufferedReader(new FileReader("src/main/resources/transaction.csv"));//reading the file
    // we are looping to read the transaction file line by line if it's not empty.
    String theLine;
    while ((theLine = transactionRead.readLine()) != null) {
        //created a product using the product class and pieces of the string .split("\\|")
        String[] transactionInfo = theLine.split("\\|");
        //If the line start with Date skip it to read the next line.
        if(transactionInfo.length !=5 || transactionInfo[0].startsWith("Date")) {
            continue;
        }
    // Use.trim() on each element to avoid parsing errors.
    // because the csv file contains extra spaces around the pipe symbol,
        LocalDate dateStamp = LocalDate.parse(transactionInfo[0].trim());
        LocalTime timeStamp = LocalTime.parse(transactionInfo[1].trim());
        String description = transactionInfo[2].trim();
        String vendor = transactionInfo[3].trim();
        double amount = Double.parseDouble(transactionInfo[4].trim());

//if (transactionInfo.length == 5){

    //instantiate a new theTransaction object from transaction class and pass in argument.
        Transaction theTransaction = new Transaction(dateStamp,timeStamp,
                description,vendor,amount);

    //add that transactions to the allTransaction array list using allTransaction.add
    allTransaction.add(theTransaction);
    }
    //Close the buffer.
    transactionRead.close();
     } catch(Exception e){
        System.out.print("Error reading Transaction file." + e.getMessage() + e.getStackTrace());
     }
     return allTransaction;
    }
/*FileReader opens the file.
*BufferedReader reads bigger chunks of text for better performance.
*While loop keeps reading each line until there are no more lines (null).
*Put what we read in an array of string by splitting it.
* Parse it when access and store it a variable.
*Create object to hold our list of items.
*Add our object to the ArrayList.
*/
// method for Showing all the transaction to display on the console
public static void showTransaction(){
//put all the transaction in the allTransaction container when this method is called
     getTransaction();  // Capture the returned list  getTransaction();
// ?made this global with static to access throughout the class.
    //Display message
    System.out.println("This is all the transactions");
    System.out.println("Date | Time | Description | Vendor | Amount ");
    //sorting the transaction in descending order.//temporary object got this method from my peers.
    allTransaction.sort((t1,t2)-> {
                int dateCompare = t2.getDate().compareTo(t1.getDate());
                if (dateCompare != 0) {
                    return dateCompare;
                }
                return t2.getTime().compareTo(t1.getTime());});//will check using the time if 2 dates are equal.
    //loop through it to  display and show :-
     for (int i = 0; i < allTransaction.size(); i++) {
         Transaction t = allTransaction.get(i);
     //display all transaction with its Date,Time,Description,Vendor, & amount.
       System.out.println(t);// because we are calling the toString method.getDate()+ " | " + t.getTime()+ " | " + t.getDescription() + " | " + t.getVendor()+ " | " + t.getAmount());
        }
     /* We call the getTransaction method that read the input data saved from csv file and add it to our ArrayList.
     * Display message will show & title
     * We sort it in descending order to get the latest first before the print/display.
     * Loop through the arrayList to get all element in the list
     * Display it on our console */
}
// ============================ End All Entries(A) ============================== //

// ============================ LEDGER Deposit Only(D) ============================== //
public static void readDeposit(){
//call this method for reading from the files
getTransaction();
//Display message
System.out.println("--------Your Deposit Information-------");
//Got this sort method from the showTransaction method copy & pasta hehe but I should create method to DRY.
allTransaction.sort((t1,t2)-> {
    int dateCompare = t2.getDate().compareTo(t1.getDate());
    if (dateCompare != 0) {
        return dateCompare;
    }
    return t2.getTime().compareTo(t1.getTime());});//will check using the time if 2 dates are equal.
for(Transaction t : allTransaction) {
    if (t.getAmount()>0){
        System.out.println(t.toString());
}
//for(int i=0; i<allTransaction.size(); i++){
//    // Get each transaction one at a time
//    Transaction t = allTransaction.get(i);
//    if(t.getAmount() > 0){
//        System.out.println(t);

}
}
/* We call the getTransaction method that read the input data saved from csv file and add it to our ArrayList.
 * Display message will show
 * We sort it in descending order to get the latest first before the print/display.
 * Loop through the arrayList to get every element/deposit in the list.
 * Display it on our console */
// ============================ End  Deposit Only ============================== //

// ============================ LEDGER Payment Only(P) ============================== //
public static void readPayment(){
//call this method for reading from the files
getTransaction();
//Display message
System.out.println("--------Your Payment Information-------");
//Got this sort method from the showTransaction method copy & pasta hehe,but I should create method to DRY.
allTransaction.sort((t1,t2)-> {
    int dateCompare = t2.getDate().compareTo(t1.getDate());
    if (dateCompare != 0) {
        return dateCompare;
    }
    return t2.getTime().compareTo(t1.getTime());});//will check using the time if 2 dates are equal.

for(int i=0; i<allTransaction.size(); i++){
    // Get each transaction one at a time
    Transaction t = allTransaction.get(i);
    if(t.getAmount() < 0){
        System.out.println(t);
    }
}
}
    /* We call the getTransaction method that read the input data saved from csv file and add it to our ArrayList.
     * Display message will show
     * We sort it in descending order to get the latest first before the print/display.
     * Loop through the arrayList to get every element/Payment in the list.
     * Display it on our console */
// ============================ End Payment Only ============================== //

//#################################Report Screen############################### //
public static void reportScreen(){

    System.out.println("-----------------------------------");
    System.out.println("Welcome to The Report Screen !!!");
    System.out.println("-----------------------------------");

    System.out.print("What would you like to do?  Select 1-5 & 0 (back).\n");
    System.out.print("1)Month To Date  \n");
    System.out.print("2) Previous Month\n");
    System.out.print("3)Year To Date \n");
    System.out.print("4)Previous Year\n");
    System.out.print("5)Search by Vendor \n");
    System.out.print("0)Back \n");//Ledger Screen
      String choice = myScanner.nextLine();
    switch (choice) {
        // If user chose "1"
        case "1":
            //Call method to read and display all transactions (deposits and payments)

            showTransaction();
            break;
        // If user chose "2"
        case "2":// Call method to read and display only deposit (positive amount) transactions
            readDeposit();
            break;
        // If user chose "3"
        case "3":
            // Call method to read and display only payment (negative amount) transactions
            readPayment();
            break;
        // If user chose "4" or "r"
        case "4":
            // Call method to display the Reports menu screen
            reportScreen();
            break;
        // If user chose "5" or "h"
        case "5":
            // Return to the Home screen
            homeScreen();
            break;
        case "0":
            // Return to the Home screen
            homeScreen();
            break;
        // If user entered any invalid option
        default:
            System.out.println("Invalid option. Try again.");
            break;
    }
}
//1=> Month to date the start of the current month to today/now
public static void monthDateReport(){
  //get the current month
  //loop through /iterate list of transaction
  //for each transaction comparing the month of the date of the transaction  to the current month
  //print if there is the match and skip if therenot a match
}




//#################################Report Screen############################### //


















}
//log file to check for the search filter later
        /*static DateTimeFormatter myTimeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM - dd  HH:mm:ss");

    //Create the scanner outside main and can be used by all method
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        //invoke the log action that take the actual value argument
        logAction("  Launch");

        // Variable for setting the condition of the loop for the questions til decided to exit.
        boolean appRunning = true;
        while (appRunning) {
            //display the question to the user
            System.out.println("Enter a search term (X to exit) :");

            //store the input from the user in a variable
            String inputSearchTerm = myScanner.nextLine();

            //do the negative thing first(you might not need an else) - to do the least amount of work first to make it simple for the mind
            if (inputSearchTerm.equalsIgnoreCase("X")) {
                System.out.println("Have a nice life");
                logAction("exit");
                appRunning = false;
            } else {
                //log the search term to the log file=> we did without the else but
                logAction("search :" + inputSearchTerm);
            }

        }

    }
    //Method that will create and maintain our log file => open a file, put something on the file,  produce a date stamp and take value and append.
    public static void logAction(String theAction) {
        //control statement when writing file we need try and catch/try and write to the file
        //allow us to try and write to the file
        try {
            //we need this to write to file
            //path where I want my file to be and ture is to append(make sure the previous instance stay) to the file not to overwrite to the file
            FileWriter myLogFile = new FileWriter("src/main/resources/logs.txt", true);//working with the file that doesn't exist
            //buffer gives us the ability to write to the myLogfile and more efficient
            BufferedWriter myBufferFile = new BufferedWriter(myLogFile);//allow us to pass content
            //create the current date and time
            LocalDateTime myTimeStamp = LocalDateTime.now();
            //creating /write our file/to the file and passed the format to our local date with space and passed the placeholder / parameter).
            myBufferFile.write(myTimeStamp.format(myTimeStampFormatter) + "" + theAction);

            //log it from top to bottom not left to right and need to close the buffer.If we want to write to the file we need to close the file
            myBufferFile.newLine();//make sure we have a new line in our file
            myBufferFile.close();

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }
}*/




