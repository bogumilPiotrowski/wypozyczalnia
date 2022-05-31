package com.auto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuAdmin {

    Store store;
    User user;

    public MenuAdmin(Store store, User user) {
        this.store = store;
        this.user = user;
    }

    public void wyswietlListeSamochodow(List<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println((i + 1) + " " + car.brand);
        }
    }

    public void wyswietlListeUzytkownikow(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.println((i + 1) + " " + user.name);
        }
    }

    public static int menuAdmin() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyświetl listę wypożyczonych teraz samochodów");
        System.out.println("     2. Wyświetl historię wypożyczeń samochodów");
        System.out.println("     3. Wyświetl listę użytkowników");
        System.out.println("     4. Wyświetl listę samochodów");
        System.out.println("     5. Wczytaj auta");
        System.out.println("     6. Zapisz auta");
        System.out.println("     0. Wyloguj się");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }

    public static int samochodmenuAdmin() {
        System.out.println();
        System.out.println("     1. Dodaj samochód");
        System.out.println("     2. Usuń samochód");
        System.out.println("     3. Edytuj samochód");
        System.out.println("     4. Wyświetl szczegóły o samochodzie");
        System.out.println("     0. Wróc");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }

    public void samochodAdmin(List<Car> carList, int i) throws IOException {
        Scanner in = new Scanner(System.in);
        int numer = 0;
        int choice = 1;

        while (choice != 0) {
            wyswietlListeSamochodow(carList);
            choice = samochodmenuAdmin();
            switch (choice) {
                case 1:
                    System.out.println("Podaj marke: ");
                    String brand = in.nextLine();
                    System.out.println("Podaj model: ");
                    String model = in.nextLine();
                    System.out.println("Podaj datę produkcji (miesiąc/dzień/rok): ");
                    String str = in.nextLine() + " 00:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                    LocalDateTime productionDate = LocalDateTime.parse(str, formatter);
                    System.out.println("Podaj licznik kilometrów: ");
                    int mileage = in.nextInt();
                    Car car = new Car(brand, model, productionDate, mileage);
                    store.addCar(car);

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 2:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        System.out.println(carList.get(numer - 1).id);
                        store.deleteCar(carList.get(numer - 1).id);
                    }

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 3:

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 4:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        Car c = store.carDetails(numer - 1);
                        System.out.println(c.brand);
                        System.out.println(c.model);
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
            }
        }
    }

    public static int uzytkownikmenuAdmin() {
        System.out.println();
        System.out.println("     1. Dodaj użytkownika");
        System.out.println("     2. Usuń użytkownika");
        System.out.println("     3. Edytuj użytkownika");
        System.out.println("     4. Wyświetl szczegóły o użytkowniku");
        System.out.println("     0. Wróc");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }

    public void uzytkownikAdmin(List<User> userList, int i) throws IOException {
        Scanner in = new Scanner(System.in);
        int numer = 0;
        int choice = 1;

        while (choice != 0) {
            wyswietlListeUzytkownikow(userList);
            choice = uzytkownikmenuAdmin();
            switch (choice) {
                case 1:
                    System.out.println("Podaj imie: ");
                    String name = in.nextLine();
                    System.out.println("Podaj hasło: ");
                    String password = in.nextLine();
                    System.out.println("Podaj kraj: ");
                    String country = in.nextLine();
                    System.out.println("Podaj miasto: ");
                    String city = in.nextLine();
                    System.out.println("Podaj ulice: ");
                    String street = in.nextLine();
                    System.out.println("Podaj numer domu lub numer domu/numer mieszkania: ");
                    String houseNumber = in.nextLine();
                    User user = new User(name, password, country, city, street, houseNumber);
                    store.addUser(user);

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 2:
                    System.out.println("Podaj numer użytkownika: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        System.out.println(userList.get(numer - 1).id);
                        store.deleteUser(userList.get(numer - 1).id);
                    }

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 3:

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 4:
                    System.out.println("Podaj numer użytkownika: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        User u = store.userDetails(numer);
                        System.out.println(u.toString());
//                      System.out.println(u.id);
//                      System.out.println(u.name);
//                      System.out.println(u.country);
//                      System.out.println(u.city);
//                      System.out.println(u.street);
//                      System.out.println(u.houseNumber);

                        System.out.println("Wciśnij Enter, aby kontynuować...");
                        System.in.read();
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("Wciśnij Enter, aby kontynuować...");
                    System.in.read();
            }
        }
    }

    public void admin(Store s) throws Exception {
        this.store = s;

        Scanner in = new Scanner(System.in);
        int i = 0;
        int choice = 1;

        while (choice != 0) {
            choice = menuAdmin();
            switch (choice) {
                case 1:
                    List<Relation> rented = store.rentedCars();
                    for (i = 0; i < rented.size(); i++) {
                        System.out.println(" Użytkownik " + rented.get(i).user.name + " id: " + rented.get(i).user.id + " wypożycza " + rented.get(i).car.brand + " id: " + rented.get(i).car.id + " od " + rented.get(i).rentalStart.toString());
                    }

                    System.out.println("\nWciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 2:

                    break;

                case 3:
                    List<User> userList = store.userList();
                    i = userList.size();
                    uzytkownikAdmin(userList, i);

                    break;

                case 4:
                    List<Car> carList = store.carList();
                    i = carList.size();
                    samochodAdmin(carList, i);

                    break;

                case 5:
                    System.out.println("Podaj ścieżkę: ");
                    String path = in.nextLine();

                    System.out.println("\nWciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 6:
                    System.out.println("Podaj ścieżkę: ");
                    String path1 = in.nextLine();

                    System.out.println("\nWciśnij Enter, aby kontynuować...");
                    System.in.read();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

                    System.out.println("\nWciśnij Enter, aby kontynuować...");
                    System.in.read();
            }
        }

        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}
