package core;

import devices.Device;

import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private static SmartHome instance;

    private List<Device> devices;

    private SmartHome() {
        devices = new ArrayList<>();
        // можно добавить начальные устройства
    }

    public static SmartHome getInstance() {
        if (instance == null) {
            instance = new SmartHome();
        }
        return instance;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getDeviceById(String id) {
        for (Device device : devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }

    public void toggleDevice(String id) {
        for (Device device : devices) {
            if (device.getId().equals(id)) {
                if (device.isOn()) {
                    device.turnOff();
                } else {
                    device.turnOn();
                }
                break;
            }
        }
    }

    public void addDevice(Device device) {
        devices.add(device);
    }
}
