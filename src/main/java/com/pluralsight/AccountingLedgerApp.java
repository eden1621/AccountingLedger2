package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class AccountingLedgerApp {
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) {






    }
    public static void homeScreen() {
        System.out.println("Welcome to Accounting Ledger App!!!");
        System.out.print("What would you like to do?  Select A,B,or C.\n");
        System.out.print("D)Add Deposit\n");
        System.out.print("P)Make Payment(Debit)\n");
        System.out.print("L)Ledger\n");
        System.out.print("X)Exit");
    }


}

