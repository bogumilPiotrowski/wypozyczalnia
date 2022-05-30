package com.auto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuUser {
    Store store;
    User user;

    public MenuUser(Store store, User user) {
        this.store = store;
        this.user = user;
    }

    public void wyswietlListeSamochodow(List<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println((i+1) + " " + car.id + " " + car.brand);
        }
    }


    public static int menuUser() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyświetl listę dostępnych samochodów");
        System.out.println("     2. Wyświetl listę wypożyczonych samochodów");
        System.out.println("     3. Wyświetl swoje dane");
        System.out.println("     0. Wyloguj się");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }

    public static int listadostepnychmenu() {
        System.out.println();
        System.out.println("     1. Wyświetl informacje o samochodzie");
        System.out.println("     2. Wypożycz samochód");
        System.out.println("     0. Wróc");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }
    public void listadostepnych(List<Car> carList, int i) throws Exception {
        Scanner in = new Scanner(System.in);
        int numer = 0;

        if (carList.isEmpty()) {
            System.out.println("Brak samochodów w bazie");
            return;
        }

        wyswietlListeSamochodow(carList);
        int choice = listadostepnychmenu();

        while(choice!=0){
            switch(choice){
                case 1:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {

                        //store.carDetails() nie znajduje id samochodu

                        System.out.println(carList.get(numer-1).id);
                        Car c = store.carDetails(carList.get(numer-1).id);
                        System.out.println(c.brand);
                        System.out.println(c.model);
                    }
                    break;

                case 2:

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("Wciśnij Enter, aby kontynuować...");
            System.in.read();

            wyswietlListeSamochodow(carList);
            choice = listadostepnychmenu();
        }
    }

    public static int listawypozyczonychmenu() {
        System.out.println();
        System.out.println("     1. Zwróć samochód");
        System.out.println("     0. Wróc");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }
    public void listawypozyczonych(List<Car> carList, int i) throws IOException {
        Scanner in = new Scanner(System.in);
        int numer = 0;
        boolean notEmpty = true;


        wyswietlListeSamochodow(carList);
        int choice = listawypozyczonychmenu();

        while(choice!=0 && notEmpty){
            switch(choice){
                case 1:
                    try {
                        System.out.println("Podaj numer samochodu: ");
                        numer = in.nextInt();
                        if (numer < 1 || numer > i) {
                            System.out.println("Niewłaściwy numer");
                        } else {

                            //TODO zamiast numer ma być car.id samochodu który chce się zwrócić
                            store.returnCar(user.id, carList.get(numer-1).id);
                        }
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Nie wypożyczasz żadnego auta");
                        choice = 0;
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            //Ewentualna informacja o karze za przetrzymanie
            System.out.println("Wciśnij Enter, aby kontynuować...");
            System.in.read();

            wyswietlListeSamochodow(carList);
            choice = listawypozyczonychmenu();
        }
    }

    public static int danemenu() {
        System.out.println();
        System.out.println("     1. Edytuj dane");
        System.out.println("     0. Wróc");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }
    public void dane() throws IOException {
        Scanner in = new Scanner(System.in);

        int choice = danemenu();

        while(choice!=0){
            switch(choice){
                case 1:

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("Wciśnij Enter, aby kontynuować...");
            System.in.read();

            choice = danemenu();
        }
    }

    public void user() throws Exception {

        int choice = menuUser();
        int i;

        while(choice!=0){
            switch(choice){
                case 1:
                    //TODO
                    List<Car> availableCars = store.availableCars();
                    i = availableCars.size();
                    listadostepnych(availableCars, i);
                    break;

                case 2:
                    List<Car> cars = new ArrayList<>();
                    try {
                        cars = store.rentListByUser(user.id);
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Nie wypożyczasz samochodu");
                    }
                    if (cars.isEmpty()) {
                        choice = 0;
                        break;
                    }
                    i = cars.size();
                    listawypozyczonych(cars, i);
                    break;

                case 3:
                    dane();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("\nWciśnij Enter, aby kontynuować...");
            System.in.read();

            choice = menuUser();
        }


        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}
