package com.example.semesterprojectver2;

public class CarInventory {
    //declare variables
    String carType;
    Double carDescription;
    int carId;

    // ALT+Insert Constructor -> check both of variables
    public CarInventory(String carType, Double carDescription, int carId) {
        this.carType = carType;
        this.carDescription = carDescription;
        this.carId = carId;
    }
    // ALT+Insert GET and SETTER -> check both of variables
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Double getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(Double carDescription) {
        this.carDescription = carDescription;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
