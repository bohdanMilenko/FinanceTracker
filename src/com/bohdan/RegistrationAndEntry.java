package com.bohdan;

import com.bohdan.Entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.bohdan.Entities.User.CUSTOMER_INFO_PATH;
import static com.bohdan.Entities.User.TRANSACTIONS_PATH;

public class RegistrationAndEntry {
    private static List<User> userList;
    private Scanner scanner = new Scanner(System.in);
    private MainMenu mainMenu = new MainMenu();

    public RegistrationAndEntry() {
        userList =  new LinkedList<>();
    }

    public void enterApplication() throws IOException{
            int response;
            boolean flag = true;
            while (flag) {
                printMenuStep1();
                response = scanner.nextInt();
                scanner.nextLine();
                switch (response) {
                    case 1:
                        registerUser();
                        mainMenu.runMainMenu();
                        flag = false;
                        break;
                    case 2:
                        loadUser();
                        loadUsersTransactions();
                        mainMenu.runMainMenu();
                        flag = false;
                        break;
                    default:
                        flag = false;
                        break;
                }
            }
        }

    private void printMenuStep1(){
        System.out.println("Please select an option below:");
        System.out.println("" +
                "\t1. Register\n" +
                "\t2. Returning User\n" +
                "\t3. Quit\n" +
                "");
    }

    public void registerUser(){
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter the starting sum");
        int amount = scanner.nextInt();
        if(amount<0){
            do{ System.out.println("Enter 0 or positive amount below:");
                amount = scanner.nextInt();
            }while (amount<0);
        }
        name = properName(name);
        userList.add(new User(name,amount));
        System.out.println("Congratulation " + name + "! You have registered.");
    }

    public String properName(String name){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static List<User> getUserList() {
        return userList;
    }

    private void loadUser() throws IOException {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        BufferedReader userInfo = new BufferedReader(new FileReader(CUSTOMER_INFO_PATH + name + "s Info.txt"));
        String input = userInfo.readLine();
        String[] userLine = input.split(",");
        double amount = Double.parseDouble(userLine[1]);
        userList.add(new User(userLine[0],amount));

    }

    private void loadUsersTransactions() throws IOException{
        User user = userList.get(0);
        BufferedReader usersTransactions = new BufferedReader(new FileReader(TRANSACTIONS_PATH + user.getName() + "s Transactions.txt"));
        String input;
        while ((input = usersTransactions.readLine())!=null){
            String[] transactionLine = input.split(",");
            String category = transactionLine[1];
            String reason = transactionLine[2];
            double amount = Double.parseDouble(transactionLine[3]);

            user.addTransaction(reason,category,amount);
            System.out.println("Transaction added, category: " + category);
        }
    }

}
