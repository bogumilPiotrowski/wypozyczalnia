package com.auto;

public class Menu {

    Store store;
    public Menu(Store s, User user) throws Exception {
        this.store = s;
        if (user.isAdmin) {
            MenuAdmin menu = new MenuAdmin(s, user);
            menu.admin(s);
        } else {
            MenuUser menu = new MenuUser(s, user);
            menu.user();
        }
    }
}
