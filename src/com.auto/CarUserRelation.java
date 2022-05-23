package com.auto;

import java.time.LocalDateTime;

public class CarUserRelation implements java.io.Serializable {

    Car car;
    User user;
    LocalDateTime rentalStart;

    LocalDateTime rentalEnd;

    public CarUserRelation(Car car, User user, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        this.car = car;
        this.user = user;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }
}
