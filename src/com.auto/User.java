package com.auto;

class User implements java.io.Serializable
{
    public int id;

    public String name;
    public String password;
    public String country;
    public String city;
    public String street;
    public String houseNumber;

    public boolean isAdmin;

    // Default constructor
    public User(String name,  String password, String country, String city, String street, String houseNumber)
    {
        this.name = name;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.id = Store.users.isEmpty() ? 0 : Store.users.stream().max((o1, o2) -> o1.id > o2.id ? 1 : -1).get().id + 1;
    }

    public User(String name,  String password, String country, String city, String street, String houseNumber, boolean isAdmin)
    {
        this.name = name;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.id = Store.users.isEmpty() ? 0 : Store.users.stream().max((o1, o2) -> o1.id > o2.id ? 1 : -1).get().id + 1;
        this.isAdmin = true;
    }

    @Override
    public String toString() {
        return "Szczegóły" +
                "\nImie: " + name +
                "\nPaństwo: " + country +
                "\nMiasto: " + city +
                "\nUlica: " + street +
                "\nHaslo: " + password +
                "\nNumer domu: " + houseNumber;
    }
}
