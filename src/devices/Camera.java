package devices;

public class Camera extends Device {

    private String resolution;

    public Camera(String id, String name, String room, String resolution) {
        super(id, name, room); // передаём room в родительский класс
        this.resolution = resolution;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Камера " + getName() + " включена. Местоположение: " + getRoom());
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

    public String getResolution() {
        return resolution;
    }
}
