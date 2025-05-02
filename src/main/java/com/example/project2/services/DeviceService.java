package com.example.project2.services;

import devices.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import core.AutomationTask;

@Service
public class DeviceService {
    private final List<Device> devices = new ArrayList<>();

    public DeviceService() {
        devices.add(new Light("1", "Люстра", "Зал", "Свет"));
        devices.add(new Camera("2", "Камера 1", "Зал", "FullHD"));
        devices.add(new HeatedFloor("3", "Пол в кухне", "Кухня", "Теплый пол"));
        devices.add(new Thermostat("4", "Термостат", "Кухня", "Климат"));
        devices.add(new HeatedFloor("5", "Пол в коридоре", "Коридор", "Теплый пол"));
        devices.add(new Thermostat("6", "Кондиционер в спальне", "Спальня", "Кондиционер"));
        devices.add(new Thermostat("7", "Кондиционер в зале", "Зал", "Кондиционер"));
        devices.add(new HumiditySensor("8", "Влажность в спальне", "Спальня", "Влажность"));
        devices.add(new HumiditySensor("9", "Влажность в зале", "Зал", "Влажность"));
    }

    public List<Device> getAllDevices() {
        return new ArrayList<>(devices);
    }

    public void updateDeviceValue(String deviceId, int value) {
        Device device = getDeviceById(deviceId);
        if (device instanceof Adjustable adjustable) {
            adjustable.setLevel(value);
        }
    }

    public Device getDeviceById(String id) {
        return devices.stream()
                .filter(device -> device.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Device> getAvailableDevices() {
        return new ArrayList<>(devices);
    }

    public Device findByName(String name) {
        return devices.stream()
                .filter(device -> device.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Device> getDevicesByRoom(String room) {
        return devices.stream()
                .filter(device -> device.getRoom().equalsIgnoreCase(room))
                .collect(Collectors.toList());
    }
    public void executeAutomationTask(AutomationTask task) {
        for (Device device : getAllDevices()) {
            if (device.getName().equals(task.getDeviceName()) &&
                    device.getRoom().equalsIgnoreCase(task.getRoomName())) {

                if (device instanceof Switchable) {
                    Switchable switchable = (Switchable) device;

                    if (task.isTurnOn()) {
                        switchable.turnOn();
                    } else {
                        switchable.turnOff();
                    }

                    System.out.println("Автоматизация: " + device.getName() + " в " + task.getRoomName()
                            + " → " + (task.isTurnOn() ? "ВКЛ" : "ВЫКЛ"));
                }

                return; // задача выполнена
            }
        }

        System.out.println("Устройство не найдено для задачи: " + task.getDeviceName());
    }

    public void setDeviceState(String deviceId, boolean state) {
        Device device = getDeviceById(deviceId);
        if (device instanceof Switchable switchable) {
            if (state) {
                switchable.turnOn();
            } else {
                switchable.turnOff();
            }
            System.out.println("Устройство " + device.getName() + " установлено в состояние: " + (state ? "включено" : "выключено"));
        } else {
            System.out.println("❌ Устройство не поддерживает включение/выключение: " + deviceId);
        }
    }

}
