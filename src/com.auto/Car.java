package com.auto;

import java.time.LocalDateTime;
import java.util.Date;

public class Car implements java.io.Serializable {
    public boolean isRental;
    public String brand;
    public String model;
    public LocalDateTime productionDate;
    public int mileage;

    public Car(String brand,String model, LocalDateTime productionDate, int  mileage)
    {
        this.isRental = false;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.mileage = mileage;
    }

    public boolean compare(Car o) {
        if( this.isRental == o.isRental &&
        this.brand.equals(o.brand) &&
        this.model.equals(o.model) &&
        this.productionDate.isEqual(o.productionDate) &&
        this.mileage == o.mileage) {
            return true;
        }
        return false;

    }
}
//https://www.tutussfunny.com/car-rentail-system-source-code-download/