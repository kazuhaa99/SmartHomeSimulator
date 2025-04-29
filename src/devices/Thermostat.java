package devices;

public class Thermostat extends Device implements Controllable {

    private double temperature;

    public Thermostat(String id, String name) {
        super(id, name);
        this.temperature = 22.0;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " thermostat turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " thermostat turned OFF.");
    }

    @Override
    public void setParameter(String key, Object value) {
        if (key.equals("temperature") && value instanceof Double) {
            temperature = (Double) value;
            System.out.println("Temperature set to " + temperature + "°C");
        }
    }
}
