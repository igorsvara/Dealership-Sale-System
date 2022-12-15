package com.dealership;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class delMain {

    private static final List<CarDealership> dealerships = new ArrayList<>();

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)  throws Exception{

        sc.useDelimiter("\n");



        int age = 0;
        String name, name2;

        while(age!= 18) {
            name = sc.next();
            age = sc.nextInt();
            name2 = sc.next();

            laCago();

            System.out.println(name + "     " + name2);
        }
        sc.close();

        /*try {
            Scanner sc = new Scanner(System.in);
            int dot = 0;
            do {
                System.out.println("insert:");
                dot = sc.nextInt();
                createDealership();
            } while (dot != 4);
            sc.close();
        } catch(Exception e) {
            System.out.println("Exception thrown: " + e);
        }*/
    }

    public static void laCago() {
        System.out.println("la sto per cagare");
        System.out.println("amo" + sc.next());

    }


    public static void createDealership() {
        Scanner subSc = new Scanner(System.in);

        String name;
        String owner;
        String address;
        int capital;

        System.out.print("\n");
        System.out.println("+++++++++++++++++++++++");
        System.out.print("Enter the new dealership name: ");
        name = subSc.nextLine();
        System.out.print("Enter the name of the new owner: ");
        owner = subSc.nextLine();
        System.out.print("Enter address of the dealership: ");
        address = subSc.nextLine();
        System.out.print("Enter the initial capital: ");
        capital = Integer.parseInt(subSc.nextLine());

        System.out.println();
        System.out.println("Creating the new dealership...");

        CarDealership newDealership = new CarDealership(name, owner, address, capital);
        dealerships.add(newDealership);

        System.out.println();
        System.out.println("DEALERSHIP CREATED!");
        System.out.println("+++++++++++++++++++++++");

        subSc.close();
    }

}
