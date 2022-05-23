package com.auto;

public class Car implements java.io.Serializable {
    public boolean isRental;
    public String brand;
    public String productionDate;
    public int mileage;

    public Car(String brand, String productionDate, int  mileage)
    {
        this.isRental = false;
        this.brand = brand;
        this.productionDate = productionDate;
        this.mileage = mileage;
    }
}
//https://www.tutussfunny.com/car-rentail-system-source-code-download/