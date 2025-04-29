package core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import devices.Device;

public class Scenario implements Serializable {
    private String name;
    private List<Device> actions = new ArrayList<>();

    public Scenario(String name) {
        this.name = name;
    }

    public void addAction(Device device) {
        actions.add(device);
    }

    public void run() {
        for (Device device : actions) {
            device.turnOn();
        }
    }

    public String getName() {
        return name;
    }

    public List<Device> getActions() {
        return actions;
    }
}
