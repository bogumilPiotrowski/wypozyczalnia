package com.auto;

import java.time.LocalDateTime;

public class Relation implements java.io.Serializable {

    Car car;
    User user;
    LocalDateTime rentalStart;

    LocalDateTime rentalEnd;

    public Relation(Car car, User user, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        this.car = car;
        this.user = user;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }
}
