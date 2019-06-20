package com.bohdan;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        RegistrationAndEntry registrationAndEntryProcess = new RegistrationAndEntry();



            System.out.println("Hello! Welcome to your pocket finance manager.");
            System.out.println("If you are a new user, you need to register first!");

            registrationAndEntryProcess.enterApplication();


            System.out.println("See you!");



        }
    }

