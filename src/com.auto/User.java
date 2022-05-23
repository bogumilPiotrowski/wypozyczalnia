package com.auto;

class User implements java.io.Serializable
{
    public String name;
    public String password;
    public String country;
    public String city;
    public String street;
    public String houseNumber;

    // Default constructor
    public User(String name,  String password, String country, String city, String street, String houseNumber)
    {
        this.name = name;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
