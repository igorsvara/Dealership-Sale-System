package com.dealership;


import java.util.ArrayList;
import java.util.List;


public class CarDealership {

    final static double PERCENT_INCREMENT = 0.2;

    private final String name;
    private final String address;
    private final String dealershipOwner;

    private final List<Employee> employees;
    private final List<Employee> firedEmployees;
    private final List<Customer> customers;
    private final List<Car> carsInStore;
    private final List<Car> carsSold;

    private int currentCapital;
    private int salaries;
    private int totalSaleMoney;
    private int totalCarCost;

    //CONSTRUCTORS
    public CarDealership(String name, String address,
                         String dealershipOwner, int initialCapital) {
        this.name = name;
        this.address = address;
        this.dealershipOwner = dealershipOwner;

        this.employees = new ArrayList<>();
        this.firedEmployees = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.carsInStore = new ArrayList<>();
        this.carsSold = new ArrayList<>();
        this.currentCapital = initialCapital;
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
        return this.currentCapital + this.totalSaleMoney - this.totalCarCost - salaries;
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
    public void setTotalSaleMoney(int totalSaleMoney) {
        this.totalSaleMoney = totalSaleMoney;
    }
    public void setTotalCarCost(int totalCarCost) {
        this.totalCarCost = totalCarCost;
    }
    public void setSalaries(int totalSalaries) {
        this.salaries = totalSalaries;
    }

    public void addCurrentCapital(int capital){

        this.currentCapital += capital;
        System.out.println("Done!");
        System.out.println("New capital: $" + this.getCapital());

    }

    public void takeCurrentCapital(int withdraw) {
        if (withdraw > this.getCapital()) {
            System.out.println(">>>" + this.getName() + " has insufficient funds. Please try again.");
        } else {
            this.currentCapital -= withdraw;
            System.out.println("Done!");
            System.out.println("New capital: $" + this.getCapital());
        }
    }

    //METHODS
    public void hireEmployee(String name, int age, String address, String phoneNumber, String email, int salary) {
       if (salary <= this.getCapital() ) {
           //Employee object
           int empId = this.employees.size() + this.firedEmployees.size() + 1;
           Employee newEmp = new Employee(empId, name, age, address, phoneNumber, email, salary);

           //Dealer object
           this.employees.add(newEmp);
           setSalaries(this.getSalaries()+salary);

           System.out.println(newEmp.getName() + " hired!");
       } else {
           System.out.println(">>>Insufficient funds to hire this person.\nCurrent capital: "+this.getCapital());
       }
    }

    public void fireEmployee(int empId) {
        boolean flag = true;
        for(Employee emp : employees) {
            if(emp.getEmployeeId() == empId) {

                this.firedEmployees.add(emp);
                this.employees.remove(emp);

                this.salaries -= emp.getSalary();

                System.out.println(emp.getName() + " fired!");

                flag = false;
            }
        }
        if (flag) {
            System.out.println(">>>There is no employee with this ID. Please try again");
        }
    }

    public void addCustomer(String name, int age, String address, String phoneNumber, String email) {

        int cusId = this.customers.size() + 1;
        Customer cus = new Customer(cusId, name, age, address, phoneNumber, email);
        customers.add(cus);

        System.out.println(cus.getName() + " added!");
    }

    public void buyCar(String brand, String model, int year, int kilometers, int buyPrice) {
        if (buyPrice <= this.getCapital()) {
            //Car object
            int newCarId = this.carsSold.size() + this.carsInStore.size() + 1;
            int sellPrice = (int) (buyPrice * (1+PERCENT_INCREMENT));
            Car newCar = new Car(newCarId, brand, model, year, kilometers, buyPrice, sellPrice, this.name);

            //Dealer object
            carsInStore.add(newCar);
            setTotalCarCost(this.totalCarCost + newCar.getBuyPrice());

            System.out.println("Car bought!");
        } else {
            System.out.println(">>>Insufficient funds to buy this car.\nCurrent capital: "+this.getCapital());
        }
    }

    public void sellCar(int carId, int empId, int cusId) {
        boolean flag = true;
        Employee emp = null;
        Customer cus = null;
        Car car = null;

        //Searching for employee
        for(Employee e : employees) {
            if (e.getEmployeeId() == empId) {
                emp = e;
                flag = false;
            }
        }
        if(flag) {
            System.out.println(">>>Wrong employee ID. Please try again.");
            flag = true;
        }

        //Searching for customer
        for(Customer c : customers) {
            if (c.getCustomerId() == cusId) {
                cus = c;
                flag = false;
            }
        }
        if (flag) {
            System.out.println(">>>Wrong customer ID. Please try again.");
            flag = true;
        }

        //Searching for car
        for (Car ca : carsInStore) {
            if (ca.getCarId() == carId) {
                car = ca;
                flag = false;
            }
        }
        if (flag) {
            System.out.println(">>>Wrong car ID. Please try again.");
            flag = true;
        }


        if (cus != null && emp != null && car != null) {
            //Dealer object
            this.carsSold.add(car);
            this.carsInStore.remove(car);
            setTotalSaleMoney(this.totalSaleMoney + car.getSellPrice());

            System.out.println("Car sold!");
            System.out.println("The car was bought for: $" + car.getBuyPrice() + " and now sold for: $" + car.getSellPrice());
            System.out.println("Which made a profit of : $" + (car.getSellPrice() - car.getBuyPrice()));
            //Car object
            car.setOwner(cus.getName());
            car.setBuyPrice(car.getSellPrice());
            car.eraseSellPrice();

            //Customer object
            cus.addCarsBought(car);

            //Employee object
            emp.addCarsSold(car);
        } else {
            System.out.println(">>>Action could not be completed.");
        }
    }

    public void printEmployees() {
        List<Employee> list = this.getEmployees();
        if (list.size() <= 0) {
            System.out.println(">>>No employees yet.");
        } else {
            System.out.println("Employees:");
            for (Employee iEmp : list) {
                System.out.printf("ID:%3s%5sNAME:%15s", iEmp.getEmployeeId(), " ", iEmp.getName());
                System.out.println();
            }
        }
    }

    public void printFiredEmployees() {
        List<Employee> list = this.getFiredEmployees();
        if (list.size() <= 0) {
            System.out.println(">>>No fired employees yet.");
        } else {
            System.out.println("Fired employees:");
            for (Employee iFEmp : list) {
                System.out.printf("ID:%3s%5sNAME:%15s", iFEmp.getEmployeeId(), " ", iFEmp.getName());
                System.out.println();
            }
        }
    }

    public void printCustomers() {
        List<Customer> list = this.getCustomers();
        if (list.size() <= 0) {
            System.out.println(">>>No customers yet.");
        } else {
            System.out.println("Customers:");
            for (Customer iCus : list) {
                System.out.printf("ID:%3s%5sNAME:%15s", iCus.getCustomerId(), " ", iCus.getName());
                System.out.println();
            }
        }
    }

    public void printInStoreCars() {
        List<Car> list = this.getCarsInStore();
        if (list.size() <= 0) {
            System.out.println(">>>No cars in store.");
        } else {
            System.out.println("Cars in store:");
            for (Car iCar : list) {
                System.out.printf("ID:%3s%3sCAR:%25s%3sPRICE:%10s", iCar.getCarId()," ", iCar.getBrand() + " " + iCar.getModel(), " ", iCar.getSellPrice());
                System.out.println();
            }
        }
    }

    public void printSoldCars() {
        List<Car> list = this.getCarsSold();
        if (list.size() <= 0) {
            System.out.println(">>>No cars sold yet.");
        } else {
            System.out.println("Sold cars:");
            for (Car iCar : list) {
                System.out.printf("ID:%3s%3sCAR:%25s%3sPRICE:%10s", iCar.getCarId(), " ", iCar.getBrand() + " " + iCar.getModel(), " ", iCar.getSellPrice());
                System.out.println();
            }
        }
    }

    public void printFullCustomer(int id) {
        boolean flag = false;
        for (Customer cus : this.getCustomers()) {
            if(cus.getCustomerId() == id) {
                System.out.printf("ID:%3s\n", cus.getCustomerId());
                System.out.printf("NAME:%15s%5sAGE:%3s%5sPHONE:%11s\n", cus.getName(), " ", cus.getAge(), " ", cus.getPhoneNumber());
                System.out.printf("ADDRESS:%20S%5sEMAIL:%20s\n", cus.getAddress(), " ", cus.getEmail());
                System.out.println("Cars customer bought:");
                System.out.println(cus.getCarsBought());
                flag = true;
            }
        }
        if (!flag)
            System.out.println("Id is not present in the system.");
    }

   /* public void printFullEmployee(int id) {

    }

    public void printFullCar(int id) {

    }*/

    public void getInfo() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%20s%20s%5s|","Dealership name:", this.getName()," ");
        System.out.printf("%20s%10s%5s\n","Capital:", ("$"+this.getCapital())," ");

        System.out.printf("%20s%20s%5s|","Owner:", this.getDealershipOwner(), " ");
        System.out.printf("%20s%10s%5s\n","Total money earned:", ("$"+this.getTotalSaleMoney()), " ");

        System.out.printf("%20s%20s%5s|", "Address:", this.getAddress(), " ");
        System.out.printf("%20s%10s%5s\n","Total money spent:", ("$"+this.getTotalCarCost()), " ");
        System.out.println("+++++++++++++++++++++");
        System.out.println("Number of employees: " + this.getNumberOfEmployees());
        System.out.println("Number of customers: " + this.getNumberOfCustomers());
        System.out.println("Cars in the store: " + this.getNumberOfCarsInStore());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void getFullInfo() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println("+++++++++++++++++++++");
        System.out.printf("%20s%20s%5s|", "OWNERSHIP", " ", " ");
        System.out.printf("%20s%20s%5s\n","FINANCE", " ", " ");

        System.out.printf("%20s%20s%5s|","Dealership name:", this.getName()," ");
        System.out.printf("%20s%10s%5s\n","Capital:", ("$"+this.getCapital())," ");

        System.out.printf("%20s%20s%5s|","Owner:", this.getDealershipOwner(), " ");
        System.out.printf("%20s%10s%5s\n","Total money earned:", ("$"+this.getTotalSaleMoney()), " ");

        System.out.printf("%20s%20s%5s|", "Address:", this.getAddress(), " ");
        System.out.printf("%20s%10s%5s\n","Total money spent:", ("$"+this.getTotalCarCost()), " ");

        System.out.printf("%20s%20s%5s|", " ", " ", " ");
        System.out.printf("%20s%10s%5s\n","Total salaries:", ("$"+this.getSalaries()), " ");


        System.out.println("+++++++++++++++++++++");
        System.out.println("LISTS");
        System.out.println();

        //Customers
        System.out.println("Number of customers: " + this.getNumberOfCustomers());
        this.printCustomers();
        System.out.println(".....................");
        //Employees
        System.out.println("Number of employees: " + this.getNumberOfEmployees());
        this.printEmployees();
        System.out.println(".....................");
        //In store cars
        System.out.println("Numbers of cars in the store: " + this.getNumberOfCarsInStore());
        this.printInStoreCars();
        System.out.println(".....................");
        //Sold cars
        System.out.println("Numbers of cars sold: " + this.getNumberOfCarsSold());
        this.printSoldCars();
        System.out.println(".....................");
        //Fired employees
        System.out.println("Numbers of fired employees: " + this.getNumberOfFiredEmployees());
        this.printFiredEmployees();
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
    }
}

