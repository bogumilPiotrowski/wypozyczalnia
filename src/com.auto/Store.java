package com.auto;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store implements java.io.Serializable {

    private static Store instance;

    public static Store Store() {
        if (instance == null) {
            System.out.println("NOWY STORE");
            instance = new Store();
        }
        return instance;
    }
    String filename = "baza.txt";

    private ArrayList<Relation> list = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

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

    public User userDetails(int userId) {
        return users.stream().filter(user -> user.id == userId).findFirst().get();
    }


    public void saveFile() {
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            carList().forEach(System.out::println);
            System.out.println("Zapisano bazę");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() throws FileNotFoundException {

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Store s = (Store) in.readObject();
            this.cars = s.cars;
            this.users = s.users;
            this.list = s.list;
            System.out.println();

            in.close();
            file.close();

            System.out.println("Wczytano bazę ");
//            return store1;
//            System.out.println("a = " + store1.list.get(0).car.brand);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
            System.out.println("Bląd");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
//        return null;
    }

    public User checkCredentials(String user, String password) {
        User userCredentials = userList().stream().filter(user1 -> user1.name.equals(user)).findFirst().orElse(null);
        if (userCredentials == null) return null;

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
        return cars.stream().filter(car -> car.id == carId).findFirst().get();
    }

    public void editCar(int carId, Car newCarDetails) {
         cars.set(cars.indexOf(carDetails(carId)), newCarDetails);
    }

    public int getNextCarId() {
      return cars.isEmpty() ? 0 : cars.stream().max((o1, o2) -> o1.id > o2.id ? 1 : -1).get().id + 1;
    }

    public int getUserNextId() {
        System.out.println(users.size());
        int i = users.isEmpty() ? 0 : users.stream().max((o1, o2) -> o1.id > o2.id ? 1 : -1).get().id + 1;
        System.out.println(i);
        return i;
    }

    public void editUser(int userId, User newUserDetails) {
        users.set(users.indexOf(userDetails(userId)), newUserDetails);
    }

}
