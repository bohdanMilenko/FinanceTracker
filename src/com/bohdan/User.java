package com.bohdan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class User {

    private String name;
    private double currentBalance;
    private List<Transactions> transactionsList;

    public User(String name, double amount) {
        this.name = name;
        this.currentBalance = amount;
        this.transactionsList = new LinkedList<>();
        loadTransactionCategories();
    }

    public boolean addTransaction(String purchaseReason, String categoryOfPurchase, double amount){
        if(amount<0){
            transactionsList.add(new Expenses(purchaseReason,categoryOfPurchase,amount));
            currentBalance+=amount;
            return true;
        }else if(amount>0){
            transactionsList.add(new Income(purchaseReason,categoryOfPurchase,amount));
            currentBalance+=amount;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void saveUser() throws IOException {

        try(BufferedWriter userFile = new BufferedWriter(new FileWriter(this.name+"s Info.txt"));
            BufferedWriter transactionData = new BufferedWriter(new FileWriter(this.name + "s Transactions.txt"));
            BufferedWriter expCategories = new BufferedWriter(new FileWriter(this.name + "s ExpCategories.txt"));
            BufferedWriter incCategories = new BufferedWriter(new FileWriter(this.name + "s IncCategories.txt"))){
            userFile.write(this.name+ "," + this.currentBalance);
            for(Transactions transaction: transactionsList){
                Transactions.Key key = transaction.getPurchaseCategoryAndType();
                String category = key.getCategoryOfPurchase();
                String transactionType = key.getType().toString();
                transactionData.write(transactionType +"," +category +"," + transaction.getPurchaseReason() +"," + transaction.getAmount() + "\n");
            }

            List<String> expCatList = Transactions.getExpenseCategoriesList();
            List<String> incCatList = Transactions.getIncomeCategoriesList();

            for (String expCatString : expCatList){
                expCategories.write(expCatString+"\n");
            }
            for(String incCatString : incCatList){
                incCategories.write(incCatString+"\n");
            }
        }

    }

    public void printBalance(){
        System.out.println(this.name + "s current balance is: $" + this.currentBalance);
    }

    private void loadTransactionCategories(){
        try{
            Transactions.loadCategories();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
