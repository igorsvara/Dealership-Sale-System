package com.dealership;

public class Car {

    private final int carId;
    private final String brand;
    private final String model;
    private final int year;
    private final int kilometers;
    private final int buyPrice;
    private final int sellPrice;

    private String owner;

    //CONSTRUCTORS
    /*public Car(Car car) {
        this.brand = car.brand;
        this.model = car.model;
        this.year = car.year;
        this.kilometers = car.kilometers;
        this.buyPrice = car.buyPrice;
        this.owner = car.owner;
    }*/

    public Car(int carId, String brand, String model, int year,
               int kilometers, int buyPrice, int sellPrice, String owner) {

        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.owner = owner;
    }

    //GETTERS
    public int getCarId() {
        return carId;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public int getKilometers() {
        return kilometers;
    }
    public int getBuyPrice() {
        return buyPrice;
    }
    public int getSellPrice() {
        return sellPrice;
    }
    public String getOwner() {
        return owner;
    }

    //SETTER
    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

}
