package devices;

public class Camera extends Device implements Switchable {

    public Camera(String id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " camera turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " camera turned OFF.");
    }
}
