package com.bridgelabz.parkinglot.entity;

import java.time.LocalTime;
import java.util.Objects;

public class Vehicle {

    public int vehicleNumber;
    public String name;
    public int plateNumber;
    public String brand;
    public String colour;
    public enum Type {SMALL, LARGE};
    Type type;

    public Vehicle(int vehicleNumber, String name, int plateNumber, String brand, String colour, Type type) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.colour = colour;
        this.type = type;
    }

    @Override
    public String toString() {
        System.out.println();
        return "Vehicle{" +
                "vehicleNumber=" + vehicleNumber +
                ", name='" + name + '\'' +
                ", plateNumber=" + plateNumber +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleNumber == vehicle.vehicleNumber &&
                plateNumber == vehicle.plateNumber &&
                Objects.equals(name, vehicle.name) &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(colour, vehicle.colour) &&
                type == vehicle.type;
    }

}
