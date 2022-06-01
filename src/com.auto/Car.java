package com.auto;

import java.time.LocalDateTime;
import java.util.Date;

public class Car implements java.io.Serializable {
    private int id;
    private boolean isRental;
    private String brand;
    private String model;
    private LocalDateTime productionDate;
    private int mileage;

    public Car(String brand,String model, LocalDateTime productionDate, int mileage)
    {
        this.isRental = false;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.mileage = mileage;
    }

    public boolean compare(Car o) {
        return this.id == o.id && this.isRental == o.isRental &&
                this.brand.equals(o.brand) &&
                this.model.equals(o.model) &&
                this.productionDate.isEqual(o.productionDate) &&
                this.mileage == o.mileage;

    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", isRental=" + isRental +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate +
                ", mileage=" + mileage +
                '}';

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRental(boolean rental) {
        isRental = rental;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public boolean isRental() {
        return isRental;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public int getMileage() {
        return mileage;
    }
}
