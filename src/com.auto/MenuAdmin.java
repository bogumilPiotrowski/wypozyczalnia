package com.auto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAdmin {

    Store store;
    User user;
    Scanner in = new Scanner(System.in);

    public MenuAdmin(Store store, User user) {
        this.store = store;
        this.user = user;
    }
    public Car fillCarDetails() throws Exception {
        Scanner in = new Scanner(System.in);
        LocalDateTime productionDate;
        System.out.println("Podaj marke: ");
        String brand = in.nextLine();
        System.out.println("Podaj model: ");
        String model = in.nextLine();
        System.out.println("Podaj datę produkcji (miesiąc/dzień/rok): ");
        String str = in.nextLine() + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        try {
            productionDate = LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            productionDate = null;
        }
        System.out.println("Podaj licznik kilometrów: ");
        int mileage = in.nextInt();
        if(brand.equals("") || model.equals("") || productionDate == null || mileage == 0) {
            System.out.println("Nieprawidłowe dane");
        } else {
            Car car = new Car(brand, model, productionDate, mileage);
            return car;
        }
        throw new InvalidCarDataException("Dane auta błędne");
    }

    public User newUser() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj imie: ");
        String name = in.nextLine();
        System.out.println("Podaj hasło: ");
        String password = in.nextLine();
        System.out.println("Podaj Państwo: ");
        String country = in.nextLine();
        System.out.println("Podaj miasto: ");
        String city = in.nextLine();
        System.out.println("Podaj ulice: ");
        String street = in.nextLine();
        System.out.println("Podaj numer domu lub numer domu/numer mieszkania: ");
        String houseNumber = in.nextLine();
        if(name.equals("") || country.equals("") || city.equals("") || street.equals("") || houseNumber.equals("") || password.equals("")) {
            System.out.println("Niepoprawne dane");
        } else {
            User user = new User(name, password, country, city, street, houseNumber);
            return user;
        }
        throw new InvalidUserDataException("Dane użytkownika błędne");
    }

    public static class InvalidCarDataException extends Exception {
        public InvalidCarDataException(String str) {
            super(str);
        }
    }

    public static class InvalidUserDataException extends Exception {
        public InvalidUserDataException(String str) {
            super(str);
        }
    }

    public static int readInt() {
        try{
            Scanner in = new Scanner(System.in);
            int w = in.nextInt();
            return w;
        }
        catch (InputMismatchException | NumberFormatException e) {
            return -1;
        }
    }

    public void wyswietlListeSamochodow(List<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println((i + 1) + " " + car.getBrand());
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
        int w = readInt();

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

        int w = readInt();

        return w;
    }

    public void samochodAdmin() throws IOException {
        Scanner in = new Scanner(System.in);
        int numer = 0;
        int choice = 1;
        String brand, model, str;
        LocalDateTime productionDate;
        DateTimeFormatter formatter;
        int mileage;
        List<Car> carList = store.carList();

        while (choice != 0) {
            wyswietlListeSamochodow(carList);
            choice = samochodmenuAdmin();
            switch (choice) {
                case 1:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                        try {
                            Car car = fillCarDetails();
                            store.addCar(car);
                            System.out.println("Dodano samochód");
                            carList = store.carList();

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
//                    }

                case 2:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    if (numer < 1 || numer > carList.size()) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        try {
                            store.deleteCar(carList.get(numer - 1).getId());
                            carList = store.carList();
                            System.out.println("Usunięto samochód");
                        } catch (Exception e) {
                            System.out.println("Nie ma samochodów do wyświetlenia");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Podaj numer samochodu: ");
                    numer = in.nextInt();
                    try {
                        Car car = fillCarDetails();
                        store.editCar(carList.get(numer - 1).getId(), car);
                        System.out.println("Dane zaktualizowano");
                        carList = store.carList();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 4:
                    System.out.println("Podaj numer samochodu: ");
                    numer = readInt();
                    if (numer < 1 || numer > carList.size()) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        Car c = store.carDetails(carList.get(numer - 1).getId());
                        System.out.println("Marka: " + c.getBrand());
                        System.out.println("Model: " + c.getModel());
                        System.out.println("Licznik: " + c.getMileage());
                    }

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");

            }
            if (choice != 0)
                wcisnijEnter("Wciśnij Enter, aby kontynuować...");
        }
    }

    private void wcisnijEnter(String x) throws IOException {
        System.out.println(x);
        System.in.read();
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
        int w = readInt();

        return w;
    }

    public void uzytkownikAdmin() throws IOException {
        Scanner in = new Scanner(System.in);
        int numer = 0;
        int choice = 1;
//        String name, password, country, street, houseNumber;
        List<User> userList = store.userList();

        while (choice != 0) {
            wyswietlListeUzytkownikow(userList);
            choice = uzytkownikmenuAdmin();
            switch (choice) {
                case 1:
//                    System.out.println("Podaj imie: ");
//                    name = in.nextLine();
//                    System.out.println("Podaj hasło: ");
//                    password = in.nextLine();
//                    System.out.println("Podaj Państwo: ");
//                    country = in.nextLine();
//                    System.out.println("Podaj miasto: ");
//                    String city = in.nextLine();
//                    System.out.println("Podaj ulice: ");
//                    street = in.nextLine();
//                    System.out.println("Podaj numer domu lub numer domu/numer mieszkania: ");
//                    houseNumber = in.nextLine();
//                    if(name.equals("") || country.equals("") || city.equals("") || street.equals("") || houseNumber.equals("") || password.equals("")) {
//                        System.out.println("Niepoprawne dane");
//                    } else {
//                        User user = new User(name, password, country, city, street, houseNumber);
//                        store.addUser(user);
//                        System.out.println("Dodano użytkownika");
//                        userList = store.userList();
//                    }
                    try {
                        User user = newUser();
                        store.addUser(user);
                        System.out.println("Dodano użytkownika");
                        userList = store.userList();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    System.out.println("Podaj numer użytkownika: ");
                    numer = readInt();
                    if (numer < 1 || numer > userList.size()) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        store.deleteUser(userList.get(numer - 1).id);
                        System.out.println("Usunięto użytkownika");
                        userList = store.userList();
                    }
                    break;

                case 3:
                    System.out.println("Podaj numer użytkownika: ");
                    numer = readInt();
                    if (numer < 1 || numer > userList.size()) {
                        System.out.println("Niewłaściwy numer");
                    } else {
//                        System.out.println("Podaj imie: ");
//                        name = in.nextLine();
//                        System.out.println("Podaj Państwo: ");
//                        country = in.nextLine();
//                        System.out.println("Podaj miasto: ");
//                        city = in.nextLine();
//                        System.out.println("Podaj ulice: ");
//                        street = in.nextLine();
//                        System.out.println("Podaj numer domu lub numer domu/numer mieszkania: ");
//                        houseNumber = in.nextLine();
//                        System.out.println("Podaj nowe hasło: ");
//                        password = in.nextLine();
//                        if (name.equals("") || country.equals("") || city.equals("") || street.equals("") || houseNumber.equals("") || password.equals("")) {
//                            System.out.println("Niepoprawne dane");
//                        } else {
//                            User newData = new User(name, password, country, city, street, houseNumber, user.id);
//                            store.editUser(userList.get(numer - 1).id, newData);
//                            System.out.println("Dane zaktualizowano");
//                            userList = store.userList();
//                        }
//                    }
                        try {
                            User user = newUser();
                            store.editUser(userList.get(numer - 1).id, user);
                            System.out.println("Dane zaktualizowano");
                            userList = store.userList();

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Podaj numer użytkownika: ");
                    numer = readInt();
                    if (numer < 1 || numer > userList.size()) {
                        System.out.println("Niewłaściwy numer");
                    } else {
                        User u = store.userDetails(userList.get(numer-1).id);
                        System.out.println(u.toString());
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }
            if (choice != 0)
                wcisnijEnter("Wciśnij Enter, aby kontynuować...");
        }
    }

    public void admin() throws Exception {

        Scanner in = new Scanner(System.in);
        int i = 0;
        int choice = 1;

        while (choice != 0) {
            choice = menuAdmin();
            switch (choice) {
                case 1:
                    try {
                        List<Relation> rented = store.rentedCars();
                        for (i = 0; i < rented.size(); i++) {
                            System.out.println(" Użytkownik " + rented.get(i).user.name + " id: " + rented.get(i).user.id + " wypożycza " + rented.get(i).car.getBrand() + " id: " + rented.get(i).car.getId() + " od " + rented.get(i).rentalStart.toString());
                        }
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Wszystkie auta są do wypożyczenia");
                    }
                    break;

                case 2:
                    try {
                        List<Relation> rented = store.rentalHistory();
                        for (i = 0; i < rented.size(); i++) {
                            Relation relation = rented.get(i);
                            System.out.println("Użytkownik " + relation.user.name + " id: " + relation.user.id
                                    + " wypożyczył " + relation.car.getBrand() + " id: " + relation.car.getId());
                            System.out.println(" od " + relation.rentalStart.toString());
                            System.out.println("do " + relation.rentalEnd);
                        }
                    } catch (Store.RentNotFoundException e) {
                        System.out.println("Historia pusta");
                    }
                    break;

                case 3:
                    uzytkownikAdmin();
                    break;

                case 4:
                    samochodAdmin();
                    break;

                case 5:
                    System.out.print("Podaj ścieżkę: ");
                    String path = in.nextLine();
                    try {
                        FileMenagement fileMenagement = new FileMenagement();
                        fileMenagement.wszytajSamochodyZPliku(path).forEach(car -> store.addCar(car));
                        System.out.println("Wczytano samochody z pliku" );
                    } catch(Exception e) {
                        System.out.println("Nie znaleziono pliku");
                    }
                    break;

                case 6:
                    System.out.print("Podaj ścieżkę: ");
                    String path1 = in.nextLine();
                    try {
                        FileMenagement fileMenagement = new FileMenagement();
                        fileMenagement.zapiszDoPliku(path1, store.carList());
                        System.out.println("Udało się zapisać samochody do pliu: " + path1);
                    } catch (IOException e) {
                        System.out.println("Błąd zapisu");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\n     Niepoprawny operator\n\n");
            }
            if (choice != 0)
                wcisnijEnter("Wciśnij Enter, aby kontynuować...");
        }
        store.saveFile();
    }
}
