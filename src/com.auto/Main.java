package com.auto;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User user = new User("Edek", "haslo", "Polska", "Laski", "Słoneczna", "5/23");
        Car car = new Car("Fiat", "22-08-2016", 56478);
        CarUserRelation carUserRelation = new CarUserRelation(car, user);
        Store store = new Store();
        store.add(carUserRelation);
//        UserStore userStore = new UserStore();
//        userStore.add(user);
        String filename = "baza.txt";

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(store);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
            System.out.println("a = " + store1.list.get(0).car.brand);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }
}