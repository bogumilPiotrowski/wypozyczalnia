package com.auto;

public class CarUserRelation implements java.io.Serializable {

    Car car;
    User user;


    public CarUserRelation(Car car, User user) {
        this.car = car;
        this.user = user;
    }
}
