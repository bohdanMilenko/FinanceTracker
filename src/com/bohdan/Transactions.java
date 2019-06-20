package com.bohdan;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Transactions {

    private String purchaseReason;
    private Key purchaseCategoryAndType;
    private double amount;
    private static List<String> expenseCategoriesList = new LinkedList<>();
    private static List<String> incomeCategoriesList = new LinkedList<>();
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }


    public enum transactionType{
        INCOME, EXPENSE
    }

    public Transactions(String purchaseReason, String categoryOfPurchase, double amount, transactionType type) {
        this.purchaseReason = purchaseReason;
        this.amount = amount;
        this.purchaseCategoryAndType = Key.createKey(categoryOfPurchase,type);
    }

    public String getPurchaseReason() {
        return purchaseReason;
    }

    public double getAmount() {
        return amount;
    }

    public Key getPurchaseCategoryAndType() {
        return purchaseCategoryAndType;
    }

    public transactionType getTransactionType(){
        return purchaseCategoryAndType.type;
    }


    static void loadCategories() throws IOException{
        try(BufferedReader expReader = new BufferedReader(new FileReader("Standard ExpCategories.txt"));
            BufferedReader incReader = new BufferedReader(new FileReader("Standard IncCategories.txt"))){

            String input;
            while ((input = expReader.readLine())!= null){
                    expenseCategoriesList.add(input);
                }
            while ((input = incReader.readLine())!= null){
                incomeCategoriesList.add(input);
            }
        }
    }

    static void addCategory(int type){
        System.out.println("Please enter the category name:");
        String categoryName = scanner.nextLine();
        if(type ==1){
            expenseCategoriesList.add(categoryName);
        }else if(type==2){
            incomeCategoriesList.add(categoryName);
        }else {
            System.out.println("Something went wrong, try again.");
        }
    }


    static List<String> getExpenseCategoriesList() {
        return expenseCategoriesList;
    }

    static List<String> getIncomeCategoriesList() {
        return incomeCategoriesList;
    }

    public static class Key{
        private String categoryOfPurchase;
        private transactionType type;

        public Key(String categoryOfPurchase, transactionType type) {
            this.categoryOfPurchase = categoryOfPurchase;
            this.type = type;
        }

        public String getCategoryOfPurchase() {
            return categoryOfPurchase;
        }

        public transactionType getType() {
            return type;
        }

        public static Key createKey( String category, transactionType type1){
            return new Key(category,type1);
        }

        @Override
        public String toString() {
            return type.toString().substring(0,1) + type.toString().substring(1).toLowerCase() + ": " + categoryOfPurchase;
        }


    }
}
