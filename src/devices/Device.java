package devices;

import java.io.Serializable;

public abstract class Device implements Serializable {
    protected String id;
    protected String name;
    protected boolean isOn;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.isOn = false;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract String getStatus(); // ← Добавить сюда

    public boolean isOn() {
        return isOn;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
