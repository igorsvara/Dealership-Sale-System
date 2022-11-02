package com.dealership;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final int employeeId;
    private final String name;
    private final int age;
    private final String address;
    private final String phoneNumber;
    private final String email;

    private int salary;

    private List<Car> carsSold = new ArrayList<Car>();

    //CONSTRUCTOR
    public Employee(int employeeId, String name, int age,
                    String address, String phoneNumber,
                    String email, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.carsSold = new ArrayList<Car>();
        this.salary = salary;
    }

    public Employee(Employee employee, List<Car> carsSold) {
        this.employeeId = employee.getEmployeeId();
        this.name = employee.getName();
        this.age = employee.getAge();
        this.address = employee.getAddress();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.salary = employee.salary;
        this.carsSold = carsSold;
    }

    //GETTER
    public int getEmployeeId() {
        return employeeId;
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
    public int getSalary() {
        return salary;
    }
    public List<Car> getCarsSold() {
        return carsSold;
    }

    //SETTER
    public void addCarsSold(Car car) {
        this.carsSold.add(car);
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}



