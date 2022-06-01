package com.auto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UserLogin {
    public UserLogin(Store store) throws Exception {

        while (true){
            Scanner input1 = new Scanner(System.in);
            System.out.println("LOGOWANIE (brak danych -> wyjście)");
            System.out.print("Wpisz nazwę użytkownika: ");
            String username = input1.nextLine();

            Scanner input2 = new Scanner(System.in);
            System.out.print("Wpisz hasło: ");
            String password = input2.nextLine();

            if (username.equals("") && password.equals("")) return;
            User user =store.checkCredentials(username, password);
            if (user == null) {
                System.out.println("Nieprawidłowe hasło lub nazwa użytkownika.");
            } else {
                System.out.println("Zalogowano się.");
                Menu menu = new Menu(store, user);
            }
        }
    }
}
