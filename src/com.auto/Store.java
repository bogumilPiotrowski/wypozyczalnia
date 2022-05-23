package com.auto;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Store implements java.io.Serializable {

    String filename = "baza.txt";

    public ArrayList<CarUserRelation> list = new ArrayList<>();

    public void add(User user, Car car, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        this.list.add(new CarUserRelation(car, user, rentalStart, rentalEnd));
    }

    public void save() {
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

    public Store read() {
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

    public boolean checkCredentials(String user, String password) {
        CarUserRelation carUserRelation = this.list.stream().filter(a -> a.user.name.equals(user)).findFirst().orElse(null);
        if (carUserRelation == null) return false;
        User userCredentials = carUserRelation.user;

        System.out.println(userCredentials.name);
        System.out.println(userCredentials.password);

        return userCredentials.name.equals(user) && userCredentials.password.equals(password);
    }

    public List<User> findByCar(Car car) {
        return this.list.stream().filter(a -> a.car.compare(car)).map(carUserRelation -> carUserRelation.user).toList();
    }

    public List<User> userList() {
        return this.list.stream().map(a -> a.user).toList();
    }

    public List<Car> carList() {
        return this.list.stream().map(a -> a.car).toList();
    }


}
