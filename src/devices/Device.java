package devices;

public abstract class Device {
    protected String id;
    protected String name;
    protected boolean on;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.on = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return on;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void performFunction();

    public String getDeviceType() {
        return this.getClass().getSimpleName();
    }

    public String getTypeIcon() {
        return switch (getDeviceType()) {
            case "Light" -> "💡";
            case "Camera" -> "📷";
            case "HeatedFloor" -> "🔥";
            case "Thermostat" -> "🌡️";
            default -> "🔧";
        };
    }
}
