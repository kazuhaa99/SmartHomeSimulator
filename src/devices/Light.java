package devices;

public class Light extends Device implements Switchable {

    public Light(String id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " light turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " light turned OFF.");
    }
}
