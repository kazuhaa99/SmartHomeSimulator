package com.example.project2.core;

import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String street;
    private String building;
    private String apartment;

    public Address() {} // Обязательный конструктор по умолчанию

    public Address(String city, String street, String building, String apartment) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    // Геттеры и сеттеры
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public String getApartment() { return apartment; }
    public void setApartment(String apartment) { this.apartment = apartment; }
}
