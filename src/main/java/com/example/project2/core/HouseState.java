package com.example.project2.core;

import java.io.Serializable;

public class HouseState implements Serializable {
    private int temperature;
    private int brightness;
    private boolean heatingOn;

    public HouseState() {}

    public HouseState(int temperature, int brightness, boolean heatingOn) {
        this.temperature = temperature;
        this.brightness = brightness;
        this.heatingOn = heatingOn;
    }

    public static HouseState defaultState() {
        return new HouseState(22, 50, false); // дефолтные значения
    }

    public int getTemperature() { return temperature; }
    public void setTemperature(int temperature) { this.temperature = temperature; }

    public int getBrightness() { return brightness; }
    public void setBrightness(int brightness) { this.brightness = brightness; }

    public boolean isHeatingOn() { return heatingOn; }
    public void setHeatingOn(boolean heatingOn) { this.heatingOn = heatingOn; }
}
