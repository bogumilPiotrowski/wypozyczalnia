package com.auto;

import java.time.LocalDateTime;
import java.util.Date;

public class Car implements java.io.Serializable {
    public int id;
    public boolean isRental;
    public String brand;
    public String model;
    public LocalDateTime productionDate;
    public int mileage;

    public Car(String brand,String model, LocalDateTime productionDate, int mileage)
    {
        this.isRental = false;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.mileage = mileage;
        this.id = Store.cars.isEmpty() ? 0 : Store.cars.stream().max((o1, o2) -> o1.id > o2.id ? 1 : -1).get().id + 1;
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
}
