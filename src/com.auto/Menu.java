package com.auto;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Date;

public class Menu {

    Store store;
    public Menu(Store s) throws IOException {
        this.store = s;
        Admin();
    }

    public static int menuAdmin() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyświetl listę samochodów");
        System.out.println("     2. Wyświetl listę użytkowników");
        System.out.println("     3. Dodaj samochód");
        System.out.println("     4. Usuń samochód");
        System.out.println("     5. Edytuj samochód");
        System.out.println("     6. Dodaj użytkownika");
        System.out.println("     7. Usuń użytkownika");
        System.out.println("     8. Edytuj użytkownika");
        System.out.println("     9. Wyloguj się");
        System.out.println("     0. Koniec");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }
    public static int menuUser() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyświetl listę samochodów");
        System.out.println("     2. Wyświetl swoje dane");
        System.out.println("     3. Wypożycz samochód");
        System.out.println("     4. Oddaj samochód");
        System.out.println("     5. Wyloguj się");
        System.out.println("     0. Koniec");

        System.out.print("\nPodaj operator: ");
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();

        return w;
    }
    public void User() throws IOException {
        Scanner in = new Scanner(System.in);

        int choice = menuUser();

        while(choice!=0){
            switch(choice){
                case 1:
                    store.carList().forEach(car -> System.out.println(car.brand));
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Wybrano wylogowanie. ");
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

    public void Admin() throws IOException {

        Scanner in = new Scanner(System.in);

        int choice = menuAdmin();

        while(choice!=0){
            switch(choice){
                case 1:
                    store.carList().forEach(car -> System.out.println(car.brand));
                    break;

                case 2:
                    store.userList().forEach(user -> System.out.println(user.name));
                    break;

                case 3:
                    System.out.println("Podaj marke: ");
                    String brand = in.nextLine();
                    System.out.println("Podaj model: ");
                    String model = in.nextLine();
                    System.out.println("Podaj datę produkcji (miesiąc/dzień/rok): ");
                    String str = in.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDateTime productionDate = LocalDateTime.parse(str, formatter);
                    System.out.println("Podaj licznik kilometrów: ");
                    int mileage = in.nextInt();
                    Car car = new Car(brand, model, productionDate, mileage);
                    break;

                case 4:
                    System.out.println("Wybierz, który samochód chcesz usunąć. ");
                    break;

                case 5:
                    System.out.println("Wybierz, który samochód chcesz edytować. ");

                    break;

                case 6:
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
                    break;

                case 7:
                    System.out.println("Wybierz, którego użytkownika chcesz usunąć. ");

                    break;

                case 8:
                    System.out.println("Wybierz, którego użytkownika chcesz edytować. ");

                    break;

                case 9:
                    System.out.println("Wybrano wylogowanie. ");
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
