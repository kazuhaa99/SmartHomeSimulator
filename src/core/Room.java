package core;

import devices.Device;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private String name;
    private List<Device> devices;

    public Room(String name) {
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void showDevices() {
        System.out.println("Devices in room " + name + ":");
        for (Device device : devices) {
            System.out.println("- " + device.getName() + " (on: " + device.isOn() + ")");
        }
    }
}
