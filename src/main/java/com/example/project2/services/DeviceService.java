package com.example.project2.services;

import devices.Adjustable;
import devices.Camera;
import devices.Device;
import devices.HeatedFloor;
import devices.Light;
import devices.Thermostat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    private final List<Device> devices = new ArrayList<>();

    public DeviceService() {
        devices.add(new Light("1", "Люстра", "Гостиная", "Лампа"));
        devices.add(new Camera("2", "Камера 1", "Гостиная", "FullHD"));
        devices.add(new HeatedFloor("3", "Пол", "Ванная", "Теплый пол"));
        devices.add(new Thermostat("4", "Термостат", "Кухня", "Климат"));
    }

    public List<Device> getAllDevices() {
        return devices;
    }

    public void updateDeviceValue(String deviceId, int value) {
        for (Device device : devices) {
            if (device.getId().equals(deviceId) && device instanceof Adjustable adjustable) {
                adjustable.setLevel(value);
            }
        }
    }

    public Device findById(String id) {
        for (Device device : devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }

    public List<Device> getAvailableDevices() {
        return new ArrayList<>(devices);
    }
}
