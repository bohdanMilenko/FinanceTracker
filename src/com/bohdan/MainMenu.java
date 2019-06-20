package com.bohdan;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private List<User> userList;
    private User user;

    public MainMenu() {

    }

    public void runMainMenu() throws IOException{
        userList = RegistrationAndEntry.getUserList();
        user = userList.get(0);
        boolean flag = true;
        while (flag) {
            int response = 0;
                if (userList.isEmpty()) {
                    flag = false;
                    System.out.println(flag);
                    break;
                }
            printMenuStep2();
            response = scanner.nextInt();

            scanner.nextLine();
            switch (response) {
                case 1:
                    user.printBalance();
                    break;
                case 2:
                    addTransaction();
                    break;
                case 3:
                     addTransactionCategory();
                     break;
                case 4:
                    printAllTransactions();
                    break;
                case 5:
                    User user = userList.get(0);
                    user.saveUser();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
    private void printMenuStep2(){
        System.out.println("Please select an option below:");
        System.out.println("" +
                "\t1. Get Balance\n" +
                "\t2. Add Transaction\n" +
                "\t3. Add Transaction Category\n" +
                "\t4. Print All Transactions\n" +
                "\t5. Save the profile with changes\n" +
                "\t6. Quit\n" +
                "");

    }


    private void addTransaction(){
        System.out.println("Press:\n\t 1 for Expense \n\t 2 for Income");
        int answer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name for transaction");
        String purchaseReason = scanner.nextLine();
        System.out.println("Enter the amount:");
        double amount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Choose the category below:");
        switch (answer){
            case 1:
                createExpenseTransaction(user,purchaseReason,amount);
                break;
            case 2:
                createIncomeTransaction(user,purchaseReason,amount);
                break;
                default:
                    break;

        }

    }

    private void addTransactionCategory(){
        int answer;
        System.out.println("Press:\n\t 1 for Expense \n\t 2 for Income");
        answer = scanner.nextInt();
        scanner.nextLine();
        Transactions.addCategory(answer);

    }


    private void createExpenseTransaction(User user, String purchaseReason,  double amount){
        List<String> expensesList = Transactions.getExpenseCategoriesList();
        printTransactionsCategories(expensesList);
        int categoryNumber = scanner.nextInt()-1;
        scanner.nextLine();
        String categoryOfPurchase = expensesList.get(categoryNumber);
        user.addTransaction(purchaseReason,categoryOfPurchase,-amount);
    }
    private void createIncomeTransaction(User user, String purchaseReason,  double amount){
        List<String> incomeList = Transactions.getIncomeCategoriesList();
        printTransactionsCategories(incomeList);
        int categoryNumber = scanner.nextInt()-1;
        scanner.nextLine();
        String categoryOfPurchase = incomeList.get(categoryNumber);
        user.addTransaction(purchaseReason,categoryOfPurchase,amount);
    }

    private void printTransactionsCategories(List<String> list){
        int i=0;
        for (String transactionCategory : list){
            System.out.println(i++ +1 + ". " + transactionCategory);
        }
    }
    private void printAllTransactions(){
        System.out.println(user.getName() + " here are yours transaction:");
        List<Transactions> transactionsList = user.getTransactionsList();
        transactionsList
                .stream()
                .sorted(Comparator.comparing(Transactions::getAmount))
                .sorted(Comparator.comparing(Transactions::getTransactionType))
                .forEach(System.out::println);
    }


}
