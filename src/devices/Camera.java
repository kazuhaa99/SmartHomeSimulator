package devices;

public class Camera extends Device {

    private String location;
    private String resolution;

    public Camera(String id, String name, String location, String resolution) {
        super(id, name);
        this.location = location;
        this.resolution = resolution;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Камера " + getName() + " включена. Местоположение: " + location);
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Камера " + getName() + " выключена.");
    }

    @Override
    public void performFunction() {
        if (isOn()) {
            System.out.println("Камера " + getName() + " записывает в " + resolution + " качестве.");
        } else {
            System.out.println("Камера " + getName() + " неактивна.");
        }
    }

    public String getLocation() {
        return location;
    }

    public String getResolution() {
        return resolution;
    }
}
