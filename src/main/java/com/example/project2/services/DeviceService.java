package com.example.project2.services;

import devices.Device;
import devices.Light;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    private List<Device> devices = new ArrayList<>();

    public DeviceService() {
        devices.add(new Light("1", "Гостиная"));
        devices.add(new Light("2", "Кухня"));
    }

    public List<Device> getAllDevices() {
        return devices;
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
}
