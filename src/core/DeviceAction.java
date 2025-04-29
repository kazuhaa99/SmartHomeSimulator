package core;

import devices.Device;

public class DeviceAction implements Action {
    private Device device;
    private boolean turnOn;

    public DeviceAction(Device device, boolean turnOn) {
        this.device = device;
        this.turnOn = turnOn;
    }

    @Override
    public void execute() {
        if (turnOn) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }

    @Override
    public String toString() {
        return (turnOn ? "Turn ON " : "Turn OFF ") + device.getName();
    }
}
