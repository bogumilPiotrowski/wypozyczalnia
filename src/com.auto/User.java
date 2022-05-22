package com.auto;

class User implements java.io.Serializable
{
    public String name;
    public String password;

    // Default constructor
    public User(String name,  String password)
    {
        this.name = name;
        this.password = password;
    }
}
