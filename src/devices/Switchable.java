package devices;

public interface Switchable {
    void turnOn();
    void turnOff();
    boolean isOn();

    String getStatus();
}
