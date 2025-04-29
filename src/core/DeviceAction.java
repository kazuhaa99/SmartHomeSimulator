package core;

import devices.Device;

public class DeviceAction implements Action {
    private final Device device;

    public DeviceAction(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn(); // Здесь можно добавить больше логики, если нужно
    }

    @Override
    public String getDescription() {
        return "Включить " + device.getName(); // Убедись, что у Device есть метод getName()
    }

    public Device getDevice() {
        return device;
    }
}
