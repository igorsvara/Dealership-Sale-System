package com.dealership;

import java.util.ArrayList;
import java.util.List;


public class CarDealership {

    final static int PRICE_INCREMENT = 200;

    private final String name;
    private final String address;
    private final String dealershipOwner;

    private List<Employee> employees = new ArrayList<>();
    private List<Employee> firedEmployees = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Car> carsInStore = new ArrayList<>();
    private List<Car> carsSold = new ArrayList<>();

    private int capital;
    private int salaries;
    private int totalSaleMoney;
    private int totalCarCost;

    //CONSTRUCTORS
    public CarDealership(String name, String address, String dealershipOwner) {
        this.name = name;
        this.address = address;
        this.dealershipOwner = dealershipOwner;

        this.employees = new ArrayList<>();
        this.firedEmployees = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.carsInStore = new ArrayList<>();
        this.carsSold = new ArrayList<>();
        this.capital = 0;
        this.salaries = 0;
        this.totalSaleMoney = 0;
        this.totalCarCost = 0;
    }

    //GETTERS
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDealershipOwner() {
        return dealershipOwner;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getFiredEmployees() {
        return firedEmployees;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Car> getCarsInStore() {
        return carsInStore;
    }

    public List<Car> getCarsSold() {
        return carsSold;
    }

    public int getNumberOfEmployees() {
        return this.employees.size();
    }

    public int getNumberOfFiredEmployees() {
        return this.firedEmployees.size();
    }

    public int getNumberOfCustomers() {
        return this.customers.size();
    }

    public int getNumberOfCarsInStore() {
        return  this.carsInStore.size();
    }

    public int getNumberOfCarsSold() {
        return this.carsSold.size();
    }

    public int getCapital() {
        return capital;
    }

    public int getSalaries() {
        return salaries;
    }

    public int getTotalSaleMoney() {
        return totalSaleMoney;
    }

    public int getTotalCarCost() {
        return totalCarCost;
    }

    public int getTotalCarBought() {
        return ( this.carsInStore.size() + this.carsSold.size() );
    }


    //SETTERS
    public void setCapital(int capital) {
        this.capital = capital;
    }
    public void setSalaries(int salaries) {
        this.salaries = salaries;
    }
    public void setTotalSaleMoney(int totalSaleMoney) {
        this.totalSaleMoney = totalSaleMoney;
    }
    public void setTotalCarCost(int totalCarCost) {
        this.totalCarCost = totalCarCost;
    }



    //METHODS
    public void hireEmployee(String name, int age, String address, String phoneNumber, String email, int salary) {
        //Employee object
        int empId = this.employees.size() + this.firedEmployees.size() + 1;
        Employee newEmp = new Employee(empId, name, age, address, phoneNumber, email, salary);

        //Dealer object
        this.employees.add(newEmp);
        this.salaries += newEmp.getSalary();
    }

    public void fireEmployee(int empId) {
        for(Employee emp : employees) {
            if(emp.getEmployeeId() == empId) {

                this.firedEmployees.add(emp);
                this.employees.remove(emp);

                this.salaries -= emp.getSalary();
            }
        }
    }

    public void addCustomer(Customer cus) {
        this.customers.add(cus);
    }

    public void buyCar(String brand, String model, int year, int kilometers, int buyPrice) {

        //Car object
        int newCarId = this.carsSold.size() + this.carsInStore.size() + 1;
        int sellPrice = buyPrice + PRICE_INCREMENT;
        Car newCar = new Car(newCarId, brand, model, year, kilometers, buyPrice, sellPrice, this.name);

        //Dealer object
        carsInStore.add(newCar);
        totalCarCost += newCar.getBuyPrice();
    }

    public void sellCar(int carId, Employee emp, Customer cus) {

        for (Car car : carsInStore) {
            if (car.getCarId() == carId) {

                //Dealer object
                this.carsSold.add(car);
                this.carsInStore.remove(car);
                this.totalSaleMoney += car.getSellPrice();

                //Car object
                car.setOwner(cus.getName());

                //Customer object
                cus.addCarsBought(car);

                //Employee object
                emp.addCarsSold(car);
            }
        }
    }
}

