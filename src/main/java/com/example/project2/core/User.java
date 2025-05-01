package com.example.project2.core;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 5470311189358783705L;

    private String username;
    private String password;
    private Address address; // 👈 добавили поле

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.address = null;
    }

    // Геттеры и сеттеры
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
