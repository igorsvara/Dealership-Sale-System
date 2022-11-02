package com.dealership;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final int customerId;
    private final String name;
    private final int age;
    private final String address;
    private final String phoneNumber;
    private final String email;

    private List<Car> carsBought = new ArrayList<>();

    //CONSTRUCTOR
    public Customer(int id, String name, int age, String address, String phoneNumber, String email) {
        this.customerId = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.carsBought = new ArrayList<Car>();
    }

    public Customer(Customer customer, List<Car> carsBought, List<Car> carsSold) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.age = customer.getAge();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();
        this.carsBought = carsBought;
    }

    //GETTERS
    public int getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public List<Car> getCarsBought() {
        return carsBought;
    }

    //SETTERS
    public void addCarsBought(Car car) {
        this.carsBought.add(car);
    }
}
