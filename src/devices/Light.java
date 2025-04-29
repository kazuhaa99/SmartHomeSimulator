package devices;

public class Light extends Device implements Adjustable {
    private int brightness;

    public Light(String id, String name, String location, String type) {
        super(id, name);
        this.brightness = 50;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Свет включён: " + name);
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Свет выключен: " + name);
    }

    @Override
    public void performFunction() {
        System.out.println("Настройка яркости света: " + brightness);
    }

    @Override
    public void setLevel(int level) {
        this.brightness = level;
    }

    @Override
    public int getValue() {
        return brightness;
    }
}
