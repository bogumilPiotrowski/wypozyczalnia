package com.auto;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Menu {

    Store store;
    public Menu(Store s, Store.UserType userType) throws Exception {
        this.store = s;
        if (userType == Store.UserType.Admin) {
            admin();
        } else {
            user();
        }
    }

    public static int menuAdmin() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyświetl listę wypożyczonych samochodów");
        System.out.println("     2. Wyświetl listę użytkowników");
        System.out.println("     3. Wyświetl listę samochodów");
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

    public void wyswietlListeSamochodow(List<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println((i+1) + " " + car.brand);
        }
    }
    public void samochodAdmin() throws IOException {
        Scanner in = new Scanner(System.in);

        List<Car> carList = store.carList();
        wyswietlListeSamochodow(carList);
        if (carList.isEmpty()) {
            System.out.println("Brak samochodów w bazie");
            return;
        }
        int i = carList.size()+1;
        int choice = samochodmenuAdmin();
        int numer = 0;

        while(choice!=0){
            switch(choice){
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
                    break;

                case 2:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        System.out.println(carList.get(numer-1).id);
                        store.deleteCar(carList.get(numer-1).id);
                    }
                    break;

                case 3:

                    break;

                case 4:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        Car c = store.showCarDetails(numer);
                        System.out.println(c.brand);
                        System.out.println(c.model);
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("\nOperacja została wykonana pomyślnie\nWciśnij Enter, aby kontynuować...");
            System.in.read();

            wyswietlListeSamochodow(carList);
            choice = samochodmenuAdmin();
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
    public void uzytkownikAdmin() throws IOException {
        Scanner in = new Scanner(System.in);

        int choice = uzytkownikmenuAdmin();

        while(choice!=0){
            switch(choice){
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
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
                    /*
                    System.out.println("Podaj numer użytkownika: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > i) {
                      System.out.println("Niewłaściwy numer");
                    } else {
                      User u = store.showUserDetails(numer);
                      System.out.println(u.id);
                      System.out.println(u.name);
                      System.out.println(u.county);
                      System.out.println(u.city);
                      System.out.println(u.street);
                      System.out.println(u.houseNumber);
                    }
                    */
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("\nOperacja została wykonana pomyślnie\nWciśnij Enter, aby kontynuować...");
            System.in.read();

            choice = uzytkownikmenuAdmin();
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
    public void listadostepnych() throws IOException {
        Scanner in = new Scanner(System.in);

        int choice = listadostepnychmenu();

        while(choice!=0){
            switch(choice){
                case 1:

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
    public void listawypozyczonych() throws IOException {
        Scanner in = new Scanner(System.in);

        int choice = listawypozyczonychmenu();

        while(choice!=0){
            switch(choice){
                case 1:

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            //Ewentualna informacja o karze za przetrzymanie
            System.out.println("Wciśnij Enter, aby kontynuować...");
            System.in.read();

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

    public void user() throws IOException {

        int choice = menuUser();

        while(choice!=0){
            switch(choice){
                case 1:
                    store.carList().forEach(car -> System.out.println(car.brand));
                    listadostepnych();
                    break;

                case 2:
                    store.carList().forEach(car -> System.out.println(car.brand));
                    listawypozyczonych();
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

    public void admin() throws Exception {

        Scanner in = new Scanner(System.in);

        int choice = menuAdmin();
        int i = 0;

        while(choice!=0){
            switch(choice){
                case 1:
                    List<Relation> rented = store.rentList();
                    for (i = 0; i < rented.size(); i++) {
                        System.out.println(" Użytkownik " + rented.get(i).user.name + " wypożycza " + rented.get(i).car.brand + " od " + rented.get(i).rentalStart.toString());
                    }
                    break;

                case 2:
                    List<User> userList = store.userList();
                    for (i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);
                        System.out.println((i+1) + " " + user.name);
                    }
                    uzytkownikAdmin();
                    break;

                case 3:
                    samochodAdmin();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }

            System.out.println("\nWciśnij Enter, aby kontynuować...");
            System.in.read();

            choice = menuAdmin();
        }


        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}
