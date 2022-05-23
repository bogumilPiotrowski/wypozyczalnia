package com.auto;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {

        User[] users = new User[]{
                new User("Edek", "haslo", "Polska", "Laski", "Słoneczna", "5/23"),
                new User("a", "a", "Niemcy", "Berlin", "Garten", "2/4"),
                new User("Ewelina", "kozak123", "Polska", "Poznan", "Glowna", "3/8"),
                new User("Marian", "zielonekije", "Czechy", "Slupsk", "Kamienna", "5"),
                new User("Agata", "wtorek13", "Polska", "Gdynia", "Polna", "3/10"),
                new User("Roksana", "trawa1", "Anglia", "Krakow", "Brzozowa", "8"),
                new User("Kacper", "kot151", "Polska", "Czestochowa", "Szkolna", "1"),
                new User("Mateusz", "krawat33", "Litwa", "Koszalin", "Krotka", "3/12"),
                new User("Karolina", "kitkat112", "Polska", "Szczecin", "Lesna", "6"),
                new User("Grazyna", "katolik134", "Polska", "Bydgoszcz", "Glowna", "9")
        };


        Car[] cars = new Car[]{
                new Car("Fiat", "cinquecento", LocalDateTime.of(2015, 2, 25, 15, 56), 56478),
                new Car("Toyota", "Corolla", LocalDateTime.of(2012, 3, 20, 8, 12), 35038),
                new Car("Skoda", "Octavia", LocalDateTime.of(2014, 7, 13, 11, 22), 44235),
                new Car("Dacia", "Duster", LocalDateTime.of(2011, 5, 7, 12, 14), 41472),
                new Car("Toyota", "Yaris", LocalDateTime.of(2012, 6, 17, 14, 52), 53129),
                new Car("Ford", "Focus", LocalDateTime.of(2010, 8, 18, 13, 37), 61465),
                new Car("Hyundai", "Tucson", LocalDateTime.of(2016, 10, 26, 17, 10), 38746),
                new Car("Kia", "Sportage", LocalDateTime.of(2017, 8, 10, 14, 27), 33875),
                new Car("Volkswagen", "Tiguan", LocalDateTime.of(2018, 4, 17, 15, 16), 41404),
                new Car("Toyota", "RAV4", LocalDateTime.of(2019, 5, 27, 9, 31), 45764)};
        Store store = new Store();

        store.add(users[1], cars[2], LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
        store.add(users[0], cars[3], LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
        store.add(users[2], cars[0], LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
        store.add(users[5], cars[5], LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));

//        store.userList().forEach(user -> System.out.println(user.name));
        store.save();

        UserLogin userLogin = new UserLogin(store);


    }
}