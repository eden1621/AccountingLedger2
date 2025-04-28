# Accounting-Ledger-App
## Description
The motivation behind this project was a class capstone assignment for Java Development Fundamentals.

I completed this project to see how much I have learned so far in building Java applications that handle user input and file storage.

By following the Acceptance Criteria,

I applied concepts like object instantiation, file writing and reading, data validation, and CLI (Command Line Interface) design.

My application allows users to track financial transactions like deposits and payments.

Each transaction is recorded into a transactions.csv file in the format: date|time|description|vendor|amount.

On the Home screen, the user can choose to add a deposit, make a payment, view the ledger, or exit the application.

The Ledger screen allows viewing all transactions, only deposits, only payments, and different types of reports like month-to-date, previous month, and search by vendor.

The program saves transactions right after the user inputs them and keeps running until the user chooses to exit.

## Table of Content
N/A

## Installation
N/A

## Usage
To use the application:

Open your Java IDE (like IntelliJ or Eclipse) or use the terminal/command prompt.

Navigate to the project folder.

Compile and run the AccountingLedgerApp Java file.

Follow the prompts shown in the console.

Example:

Choose D to add a new deposit.

Enter the required information (date, time, description, vendor, amount).

Choose P to record a payment.

Choose L to view ledger entries and reports.

Choose X to exit the application.

Every transaction will be saved automatically to the transactions.csv file located in /src/main/resources/.

## Screenshots
Home Screen:
!( ) [ ]

Ledger Screen:
!( ) [ ]

Reports Screen:
!( ) [ ]

(You can replace the paths above with your actual screenshot file paths or URLs when ready.)

## Credit
Thanks to my instructor, tutor center support, my peers, lots of Googling, and AI assistance for helping me complete this project. Their support helped me figure out how to approach building the screens, handle file operations, and debug issues along the way.

## Interesting Piece of Code
One interesting part of my project was how I created a Transaction object to capture user input and save it properly.

java
Copy
Edit
Transaction transaction = new Transaction(date, time, description, vendor, amount);
transactionList.add(transaction);
ledgerService.saveTransaction(transaction);
This made it easier to organize the transaction data and made the application more readable and maintainable.

License
N/A
