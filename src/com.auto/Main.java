package com.auto;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {

        Store store = new Store();

        store.addUser(new User("Edek", "haslo", "Polska", "Laski", "Słoneczna", "5/23"));
        store.addUser(new User("a", "a", "Niemcy", "Berlin", "Garten", "2/4", true));
        store.addUser(new User("b", "b", "Polska", "Poznan", "Glowna", "3/8"));
        store.addUser(new User("Marian", "zielonekije", "Czechy", "Slupsk", "Kamienna", "5"));
        store.addUser(new User("Agata", "wtorek13", "Polska", "Gdynia", "Polna", "3/10"));
        store.addUser(new User("Roksana", "trawa1", "Anglia", "Krakow", "Brzozowa", "8"));
        store.addUser(new User("Kacper", "kot151", "Polska", "Czestochowa", "Szkolna", "1"));
        store.addUser(new User("Mateusz", "krawat33", "Litwa", "Koszalin", "Krotka", "3/12"));
        store.addUser(new User("Karolina", "kitkat112", "Polska", "Szczecin", "Lesna", "6"));
        store.addUser(new User("Grazyna", "katolik134", "Polska", "Bydgoszcz", "Glowna", "9"));

        store.addCar(new Car("Fiat", "cinquecento", LocalDateTime.of(2015, 2, 25, 15, 56), 56478));
        store.addCar(new Car("Toyota", "Corolla", LocalDateTime.of(2012, 3, 20, 8, 12), 35038));
        store.addCar(new Car("Skoda", "Octavia", LocalDateTime.of(2014, 7, 13, 11, 22), 44235));
        store.addCar(new Car("Dacia", "Duster", LocalDateTime.of(2011, 5, 7, 12, 14), 41472));
        store.addCar(new Car("Toyota", "Yaris", LocalDateTime.of(2012, 6, 17, 14, 52), 53129));
        store.addCar(new Car("Ford", "Focus", LocalDateTime.of(2010, 8, 18, 13, 37), 61465));
        store.addCar(new Car("Hyundai", "Tucson", LocalDateTime.of(2016, 10, 26, 17, 10), 38746));
        store.addCar(new Car("Kia", "Sportage", LocalDateTime.of(2017, 8, 10, 14, 27), 33875));
        store.addCar(new Car("Volkswagen", "Tiguan", LocalDateTime.of(2018, 4, 17, 15, 16), 41404));
        store.addCar(new Car("Toyota", "RAV4", LocalDateTime.of(2019, 5, 27, 9, 31), 45764));


        store.rentCar(1, 2, LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
        store.rentCar(0, 3, LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
        store.rentCar(2, 0, LocalDateTime.of(2018, 4, 17, 15, 16), null);
        store.rentCar(5, 5, LocalDateTime.of(2018, 4, 17, 15, 16), null);

        store.userList().forEach(System.out::println);
        store.carList().forEach(System.out::println);

//        store.userList().forEach(user -> System.out.println(user.name));
        store.saveFile();

        UserLogin userLogin = new UserLogin(store);


    }
}