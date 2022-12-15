package com.dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI {

    private static List<CarDealership> dealerships = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        sc.useDelimiter("\n");
        int startMenu;

        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to CDMS the Car Dealership Management System");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");

        //sample scenario
        sample();

        do {
            System.out.println();
            System.out.println("MAIN MENU:");
            System.out.println("[1]Create new dealership    [2]View existing dealership    [3]Manage existing dealership    [4]Exit");

            startMenu = Integer.parseInt(sc.next());

            switch (startMenu) {
                case 1:
                    createDealership();
                    break;
                case 2:
                    viewDealerships();
                    break;
                case 3:
                    if (dealerships.size() == 0) {
                        System.out.println(">>>There is no dealership yet in the system, please add ono.");
                        break;
                    }
                    CarDealership selectedDealership = selectDealer();
                    manageDealership(selectedDealership);
                    break;
                case 4:
                    break;
                default : System.out.println(">>>Invalid choice.");
            }

        } while(startMenu != 4);

        System.out.println("Goodbye!");

        sc.close();
    }

    public static void createDealership() {

        String name;
        String owner;
        String address;
        int capital;

        System.out.print("\n");
        System.out.println("+++++++++++++++++++++++");
        System.out.print("Enter the new dealership name: ");
        name = sc.next();
        System.out.print("Enter the name of the new owner: ");
        owner = sc.next();
        System.out.print("Enter address of the dealership: ");
        address = sc.next();
        System.out.print("Enter the initial capital: ");
        capital = Integer.parseInt(sc.next());

        System.out.println();
        System.out.println("Creating the new dealership...");

        CarDealership newDealership = new CarDealership(name, owner, address, capital);
        dealerships.add(newDealership);

        System.out.println();
        System.out.println("DEALERSHIP CREATED!");
        System.out.println("+++++++++++++++++++++++");

    }

    private static void viewDealerships() {
        if (dealerships.size() == 0) {
            System.out.println(">>>There is no dealership yet in the system, please add ono.");
        } else {
            int i = 1;
            System.out.println("There are currently " + dealerships.size() + " dealerships in the system:");
            for (CarDealership dealership : dealerships) {
                System.out.println();
                System.out.println(i + ".");
                dealership.getInfo();
                i++;
            }
        }
    }

    public static CarDealership selectDealer() {

        System.out.println("Choose one of the available dealership:");

        int i = 1;
        for (CarDealership dealer : dealerships) {
            System.out.println("["+i+"]"+dealer.getName());
            i++;
        }
        int indexCar = 0;

        do {
            System.out.print("Which one would you like to access? ");
            indexCar = sc.nextInt();
        } while(indexCar < 1 || indexCar >= i);

        return dealerships.get(indexCar-1);

    }

    public static void manageDealership(CarDealership dealer) {

        int in = -1;

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("MANAGEMENT:");
        System.out.println("GENERAL INFO:");
        dealer.getInfo();

        while (in != 0) {
            System.out.println();
            System.out.println("Options:");
            System.out.println("[0]Main Menu");
            System.out.println("[1]Get dealership's full information");
            System.out.println("[2]Get customers list");
            System.out.println("[3]Get employees list");
            System.out.println("[4]Get fired employees list");
            System.out.println("[5]Get in store cars list");
            System.out.println("[6]Get sold cars list");
            System.out.println("[7]Deposit money");
            System.out.println("[8]Withdraw money");
            System.out.println("[9]Hire employee");
            System.out.println("[10]Fire employee");
            System.out.println("[11]Add customer");
            System.out.println("[12]Buy car");
            System.out.println("[13]Sell car");

            in = sc.nextInt();

            switch (in) {
                //EXIT
                case 0:
                    break;
                //FULL INFO
                case 1:
                    dealer.getFullInfo();
                    break;
                //CUSTOMERS LIST
                case 2:
                    dealer.printCustomers();
                    if (dealer.getCustomers().size() > 0) {
                        System.out.print("Would you like to see full information for a specific customer? [y/n] ");
                        if (sc.next().equals("y")) {
                            System.out.print("Who would you like to see? [Id] ");
                            int tempID = sc.nextInt();
                            dealer.printFullCustomer(tempID);
                        }
                    }
                    break;
                //EMPLOYEES LIST
                case 3:
                    dealer.printEmployees();
                    break;
                //FIRED EMPLOYEES LIST
                case 4:
                    dealer.printFiredEmployees();
                    break;
                //IN STORE CARS LIST
                case 5:
                    dealer.printInStoreCars();
                    break;
                //SOLD CARS LIST
                case 6:
                    dealer.printSoldCars();
                    break;
                //DEPOSIT MONEY
                case 7:
                    System.out.println("Your current capital is: $" + dealer.getCapital());
                    System.out.print("How much would you like to invest in "+dealer.getName()+ " dealership? ");
                    int capital = sc.nextInt();
                    dealer.addCurrentCapital(capital);
                    break;
                //WITHDRAW MONEY
                case 8:
                    System.out.println("Your current capital is: $" + dealer.getCapital());
                    System.out.print("How much would you like to withdraw? ");
                    int withdraw = sc.nextInt();
                    dealer.takeCurrentCapital(withdraw);
                    break;
                //HIRE EMPLOYEE
                case 9:
                    String name;
                    int age;
                    String address;
                    String phoneNumber;
                    String email;
                    int salary;

                    System.out.print("Enter employee name: ");
                    name = sc.next();
                    System.out.print("Enter employee age: ");
                    age = sc.nextInt();
                    System.out.print("Enter employee address: ");
                    address = sc.next();
                    System.out.print("Enter employee phone number: ");
                    phoneNumber = sc.next();
                    System.out.print("Enter employee email: ");
                    email = sc.next();
                    System.out.println("Available finances: $" + dealer.getCapital());
                    System.out.print("Enter employee salary: ");
                    salary = sc.nextInt();

                    System.out.println();
                    dealer.hireEmployee(name, age,address, phoneNumber, email, salary);
                    break;
                //FIRE EMPLOYEE
                case 10:
                    dealer.printEmployees();
                    System.out.print("Who do you want to fire? [ID] ");
                    dealer.fireEmployee(sc.nextInt());
                    break;
                //ADD CUSTOMER
                case 11:
                    System.out.print("Enter customer name: ");
                    name = sc.next();
                    System.out.print("Enter customer age: ");
                    age = sc.nextInt();
                    System.out.print("Enter customer address: ");
                    address = sc.next();
                    System.out.print("Enter customer phone number: ");
                    phoneNumber = sc.next();
                    System.out.print("Enter customer email: ");
                    email = sc.next();

                    System.out.println();
                    dealer.addCustomer(name, age, address, phoneNumber, email);
                    break;
                //BUY CAR
                case 12:
                    String brand;
                    String model;
                    int year;
                    int kilometers;
                    int price;

                    System.out.println("Which car would you like to buy?");
                    System.out.print("Brand? ");
                    brand = sc.next();
                    System.out.print("Model? ");
                    model = sc.next();
                    System.out.print("Year? ");
                    year = sc.nextInt();
                    System.out.print("Kilometers? ");
                    kilometers = sc.nextInt();
                    System.out.println("Available finance: $" + dealer.getCapital());
                    System.out.print("Price? ");
                    price = sc.nextInt();

                    dealer.buyCar(brand,model,year,kilometers,price);

                    break;
                //SELL CAR
                case 13:
                    int buyer;
                    int seller;

                    dealer.printCustomers();
                    System.out.print("Who is buying the car? [ID] ");
                    buyer = sc.nextInt();
                    dealer.printEmployees();
                    System.out.print("Who is selling the car? [ID] ");
                    seller = sc.nextInt();
                    dealer.printInStoreCars();
                    System.out.print("Which car would you like to sell? [ID] ");

                    dealer.sellCar(sc.nextInt(), seller, buyer);
                    break;
                default:
                    System.out.println(">>>Invalid choice");
            }
        }

    }


    public static void sample() {


        CarDealership newDealership = new CarDealership("Panuz", "Via dei Giardini 31",
                                                        "Igor", 100000);

        newDealership.hireEmployee("Tony", 25, "Via delle Meraviglie 2",
                            "3341265865", "tony@gmail.com", 1800);
        newDealership.hireEmployee("Mike", 30, "Via Milano 65",
                            "3456251459", "mike@gmail.com", 2000);
        //Firing Mike
        newDealership.fireEmployee(1);
        newDealership.addCustomer("Lorenzo", 45, "Via Maremma 5",
                            "3516849764", "lorenzo@gmail.com");
        newDealership.addCustomer("Anna", 32, "Via Stocuzz 35",
                            "3168497588", "anna@gmail.com");
        newDealership.buyCar("Ford", "Puma", 2010,
                            150600, 25000);
        newDealership.buyCar("Volkswagen", "Golf 4",
                            2003, 161000, 2500);
        //selling ford puma
        newDealership.sellCar(1, 2, 1);


        dealerships.add(newDealership);

    }

}
