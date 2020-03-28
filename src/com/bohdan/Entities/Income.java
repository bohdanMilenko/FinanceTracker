package com.bohdan.Entities;

public class Income extends  Transactions{

    public Income(String purchaseReason, String categoryOfPurchase, double amount) {
        super(purchaseReason, categoryOfPurchase, amount, transactionType.INCOME);
    }

    @Override
    public String toString() {
        return getPurchaseCategoryAndType() + " -> "+ getAmount();
    }



}
