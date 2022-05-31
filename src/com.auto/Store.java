package com.auto;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store implements java.io.Serializable {

    String filename = "baza.txt";

    static public ArrayList<Relation> list = new ArrayList<>();
    static public ArrayList<Car> cars = new ArrayList<>();
    static public ArrayList<User> users = new ArrayList<>();

    public void rentCar(int userId, int carId, LocalDateTime rentalStart, LocalDateTime rentalEnd) throws Exception {

        System.out.println(userId + " " + carId);
        Car c = cars.stream().filter(car -> car.id == carId).findFirst().orElse(null);
        if (c == null)
            throw new CarNotFoundException("Nie ma auta o indexie: " + carId);

        User u = users.stream().filter(user -> user.id == userId).findFirst().orElse(null);
        if (u == null)
            throw new UserNotFoundException("Nie ma użytkownika o indexie: " + userId);

        list.add(new Relation(
                c,
                u,
                rentalStart,
                rentalEnd)
        );
    }

    public void returnCar(int userId, int carId) throws Exception {

        System.out.println(userId + " " + carId);
        Car c = cars.stream().filter(car -> car.id == carId).findFirst().orElse(null);
        if (c == null)
            throw new CarNotFoundException("Relacja nie może zostać utworzona. Nie ma takiego auta.");

        User u = users.stream().filter(user -> user.id == userId).findFirst().orElse(null);
        if (u == null)
            throw new UserNotFoundException("Relacja nie może zostać utworzona. Nie ma takiego użytkownika.");

        Relation relation = list.stream().filter(x -> x.rentalEnd == null && x.car.compare(c) && x.user.id == userId).findFirst().orElse(null);
        if (relation == null) {
            throw new RentNotFoundException("Nie wypożyczasz żadnego auta");
        }

        System.out.println("Zwracam auto" + list.get(list.indexOf(relation)).car.brand);
        System.out.println(list.get(list.indexOf(relation)).car.brand);
        list.get(list.indexOf(relation)).rentalEnd = LocalDateTime.now();
        System.out.println("Zwrocono" + list.get(list.indexOf(relation)).rentalEnd);
    }

    public List<Relation> rentedCars() throws Exception {
        List<Relation> rented = list.stream().filter(x -> x.rentalEnd == null).toList();
        if (rented.isEmpty()) {
            throw new RentNotFoundException("Nie wypożyczasz żadnego auta");
        }

        return rented;
    }

    public List<Car> availableCars() throws Exception {

        Set<Car> a = new HashSet<>(cars);
        Set<Car> b = new HashSet<>(rentedCars().stream().map(x -> x.car).toList());
        a.removeAll(b);
        List<Car> rented = List.copyOf(a);
        if (rented.isEmpty()) {
            throw new RentNotFoundException("Brak dostępnych samochodów");
        }

        return rented;
    }

    public List<Car> rentListByUser(int userId) throws RentNotFoundException {
        List<Car> rented = list.stream().filter(x -> x.rentalEnd == null && x.user.id == userId).map(c -> c.car).toList();
        if (rented.isEmpty()) {
            throw new RentNotFoundException("Nie wypożyczasz żadnego auta");
        }
        return rented;
    }


    public static class RentNotFoundException extends Exception {
        public RentNotFoundException(String str) {
            super(str);
        }
    }
    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String str) {
            super(str);
        }
    }
    public static class CarNotFoundException extends Exception {
        public CarNotFoundException(String str) {
            super(str);
        }
    }

    public void addCar(Car car) {
        System.out.println(car);
        cars.add(car);
    }

    public void addUser(User user) {
        System.out.println(user);
        users.add(user);
    }


    public void deleteUser(int userId) {
         System.out.println("Usuwam użytkownika: " + users.stream().filter(user -> user.id == userId).findFirst().orElse(null));
         list.removeIf(relation -> relation.user.id == userId);
         users.removeIf(user -> user.id == userId);
    }

    public User userDetails(int userId) { return users.get(userId-1); }


    public void saveFile() {
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Store readFile() {
        Store store1 = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            store1 = (Store) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
//            System.out.println("a = " + store1.list.get(0).car.brand);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        return store1;
    }

    enum UserType {
        Unautorized,
        User,
        Admin
    }

    public User checkCredentials(String user, String password) {
        Relation relation = list.stream().filter(a -> a.user.name.equals(user)).findFirst().orElse(null);
        if (relation == null) return null;
        User userCredentials = relation.user;

        System.out.println(userCredentials.name);
        System.out.println(userCredentials.password);

        if (!userCredentials.name.equals(user) || !userCredentials.password.equals(password))
            return null;
        return userCredentials;
    }

    public List<User> findByCar(Car car) {
        return list.stream().filter(a -> a.car.compare(car)).map(relation -> relation.user).toList();
    }

    public List<User> userList() {
        return users;
    }

    public List<Car> carList() {
        return cars;
    }

    public void deleteCar(int carId) {
        System.out.println("Usuwam samochód: " + cars.stream().filter(car -> car.id == carId).findFirst().orElse(null));
        list.removeIf(relation -> relation.car.id == carId);
        cars.removeIf(car -> car.id == carId);
    }

    public Car carDetails(int carId) {
        return cars.get(carId);
    }

    public Car editCar(int carId) {
        return null;
    }

    public void editUser(int userId, User newUserDetails) {
        users.set(users.indexOf(userDetails(userId)), newUserDetails);
    }

}
