package core;

import java.io.Serializable;

public class RoomState implements Serializable {
    private int floorHeatingTemp;
    private int lightBrightness;
    private int airTemp;
    private int humidity;

    public RoomState() {
        this.floorHeatingTemp = 25;
        this.lightBrightness = 50;
        this.airTemp = 22;
        this.humidity = 45;
    }

    public int getFloorHeatingTemp() {
        return floorHeatingTemp;
    }

    public void setFloorHeatingTemp(int floorHeatingTemp) {
        this.floorHeatingTemp = floorHeatingTemp;
    }

    public int getLightBrightness() {
        return lightBrightness;
    }

    public void setLightBrightness(int lightBrightness) {
        this.lightBrightness = lightBrightness;
    }

    public int getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(int airTemp) {
        this.airTemp = airTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
