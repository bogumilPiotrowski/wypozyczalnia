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
            System.out.println((i+1) + " " + car.brand);
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
        int choice = 1;

        if (carList.isEmpty()) {
            System.out.println("Brak samochodów w bazie");
            return;
        }

        while(choice!=0){
            wyswietlListeSamochodow(carList);
            choice = listadostepnychmenu();
            switch(choice){
                case 1:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        System.out.println(carList.get(numer-1).id);
                        Car c = store.carDetails(carList.get(numer-1).id);
                        System.out.println(c.brand);
                        System.out.println(c.model);
                    }

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 2:

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;
            }
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
        int choice = 1;

        while(choice!=0){
            if(carList.isEmpty()) {
                System.out.println("Nie wypożyczasz żadnego auta");
                choice = 0;
            }
            wyswietlListeSamochodow(carList);
            choice = listawypozyczonychmenu();
            switch(choice){
                case 1:
                    try {
                        System.out.println("Podaj numer samochodu: ");
                        numer = in.nextInt();
                        if (numer < 1 || numer > i) {
                            System.out.println("Niewłaściwy numer");
                        } else {
                            store.returnCar(user.id, carList.get(numer-1).id);
                            carList = store.rentListByUser(user.id);
                        }
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Nie wypożyczasz żadnego auta");
                        choice = 0;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;
            }

            //Ewentualna informacja o karze za przetrzymanie
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
        int choice = 1;

        while(choice!=0){
            choice = danemenu();
            switch(choice){
                case 1:

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;
            }
        }
    }

    public void user() throws Exception {

        int choice = 1;
        int i;

        while(choice!=0){
            choice = menuUser();
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
                        i = cars.size();
                        listawypozyczonych(cars, i);
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Nie wypożyczasz samochodu");

                        System.out.println("Wciśnij Enter, aby kontynuować...");
                        System.in.read();
                    }
                    break;

                case 3:
                    dane();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("\nWciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;
            }
        }

        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}
