package com.auto;

import java.util.ArrayList;

public class UserStore implements java.io.Serializable {

    public ArrayList<User> list = new ArrayList<>();

    public void add(User user) {
        this.list.add(user);
    }
}
