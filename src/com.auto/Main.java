package com.auto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void mainn(String[] args) throws IOException {
        FileMenagement fileMenagement = new FileMenagement();
        fileMenagement.wszytajSamochodyZPliku("Cars1.txt").forEach(System.out::println);


        ArrayList<Car> store= new ArrayList<>();
        store.add(new Car("Fiat", "cinquecento", LocalDateTime.of(2015, 2, 25, 15, 56), 56478));
        store.add(new Car("Toyota", "Corolla", LocalDateTime.of(2012, 3, 20, 8, 12), 35038));

        fileMenagement.zapiszDoPliku("Cars1.txt", store);
    }
    public static void main(String[] args) throws Exception {



//
//        Store.Store().addUser(new User("Edek", "haslo", "Polska", "Laski", "Słoneczna", "5/23"));
//        Store.Store().addUser(new User("a", "a", "Niemcy", "Berlin", "Garten", "2/4", true));
//        Store.Store().addUser(new User("b", "b", "Polska", "Poznan", "Glowna", "3/8"));
//        Store.Store().addUser(new User("Marian", "zielonekije", "Czechy", "Slupsk", "Kamienna", "5"));
//        Store.Store().addUser(new User("Agata", "wtorek13", "Polska", "Gdynia", "Polna", "3/10"));
//        Store.Store().addUser(new User("Roksana", "trawa1", "Anglia", "Krakow", "Brzozowa", "8"));
//        Store.Store().addUser(new User("Kacper", "kot151", "Polska", "Czestochowa", "Szkolna", "1"));
//        Store.Store().addUser(new User("Mateusz", "krawat33", "Litwa", "Koszalin", "Krotka", "3/12"));
//        Store.Store().addUser(new User("Karolina", "kitkat112", "Polska", "Szczecin", "Lesna", "6"));
//        Store.Store().addUser(new User("Grazyna", "katolik134", "Polska", "Bydgoszcz", "Glowna", "9"));
//
//        Store.Store().addCar(new Car("Fiat", "cinquecento", LocalDateTime.of(2015, 2, 25, 15, 56), 56478));
//        Store.Store().addCar(new Car("Toyota", "Corolla", LocalDateTime.of(2012, 3, 20, 8, 12), 35038));
//        Store.Store().addCar(new Car("Skoda", "Octavia", LocalDateTime.of(2014, 7, 13, 11, 22), 44235));
//        Store.Store().addCar(new Car("Dacia", "Duster", LocalDateTime.of(2011, 5, 7, 12, 14), 41472));
//        Store.Store().addCar(new Car("Toyota", "Yaris", LocalDateTime.of(2012, 6, 17, 14, 52), 53129));
//        Store.Store().addCar(new Car("Ford", "Focus", LocalDateTime.of(2010, 8, 18, 13, 37), 61465));
//        Store.Store().addCar(new Car("Hyundai", "Tucson", LocalDateTime.of(2016, 10, 26, 17, 10), 38746));
//        Store.Store().addCar(new Car("Kia", "Sportage", LocalDateTime.of(2017, 8, 10, 14, 27), 33875));
//        Store.Store().addCar(new Car("Volkswagen", "Tiguan", LocalDateTime.of(2018, 4, 17, 15, 16), 41404));
//        Store.Store().addCar(new Car("Toyota", "RAV4", LocalDateTime.of(2019, 5, 27, 9, 31), 45764));
//
//
//        Store.Store().rentCar(1, 2, LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
//        Store.Store().rentCar(0, 3, LocalDateTime.of(2018, 4, 17, 15, 16), LocalDateTime.of(2018, 4, 25, 15, 16));
//        Store.Store().rentCar(2, 0, LocalDateTime.of(2018, 4, 17, 15, 16), null);
//        Store.Store().rentCar(5, 5, LocalDateTime.of(2018, 4, 17, 15, 16), null);
//        Store.Store().rentCar(1, 3, LocalDateTime.of(2018, 4, 17, 15, 16), null);
//        Store.Store().rentCar(2, 6, LocalDateTime.of(2018, 4, 17, 15, 16), null);
//        Store.Store().rentCar(7, 8, LocalDateTime.of(2018, 4, 17, 15, 16), null);
//
//        Store.Store().returnCar(2,0);
//
//        Store.Store().saveFile();

        try {
            Store.Store().readFile();
            UserLogin userLogin = new UserLogin(Store.Store());
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku: " + Store.Store().filename);
        }

    }
}