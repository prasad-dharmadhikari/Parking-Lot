package com.bridgelabz.parkinglot.entity;

import java.util.Objects;

public class Vehicle {

    public String name;
    public int plateNumber;
    public String brand;
    public String colour;
    public DriverType driverType;

    public Vehicle(String name, int plateNumber, String brand, String colour) {
        this.name = name;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.colour = colour;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", plateNumber=" + plateNumber +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                ", driverType=" + driverType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return plateNumber == vehicle.plateNumber &&
                Objects.equals(name, vehicle.name) &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(colour, vehicle.colour) &&
                driverType == vehicle.driverType;
    }

}
