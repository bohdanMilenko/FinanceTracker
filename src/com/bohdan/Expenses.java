package com.bohdan;

public class Expenses extends  Transactions{



    public Expenses(String purchaseReason, String categoryOfPurchase, double amount) {
        super(purchaseReason, categoryOfPurchase, amount, transactionType.EXPENSE);
    }

    @Override
    public String toString() {
        return getPurchaseCategoryAndType() + " -> " + getAmount();
    }



}
