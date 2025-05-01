package devices;

public abstract class Device {
    protected String id;
    protected String name;
    protected boolean on;
    protected String room;

    public Device(String id, String name, String room) {
        this.id = id;
        this.name = name;
        this.room = room;
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

    public String getRoom() {
        return room;
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
            case "HumiditySensor" -> "💧";
            default -> "🔧";
        };
    }
}
