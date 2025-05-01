package core;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Room implements Serializable {
    private String name;
    private double temperature;
    private int brightness;
    private double humidity;

    @JsonCreator
    public Room(
            @JsonProperty("name") String name,
            @JsonProperty("temperature") double temperature,
            @JsonProperty("brightness") int brightness,
            @JsonProperty("humidity") double humidity) {
        this.name = name;
        this.temperature = temperature;
        this.brightness = brightness;
        this.humidity = humidity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                ", brightness=" + brightness +
                ", humidity=" + humidity +
                '}';
    }
}
