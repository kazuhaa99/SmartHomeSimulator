package devices;

public class Light extends Device implements Adjustable {
    private int brightness;
    private String type;

    public Light(String id, String name, String room, String type) {
        super(id, name, room);
        this.brightness = 50;
        this.type = type;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Свет включён: " + name + " в комнате " + getRoom());
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Свет выключен: " + name + " в комнате " + getRoom());
    }

    @Override
    public void performFunction() {
        System.out.println("Настройка яркости света: " + brightness + " в комнате " + getRoom());
    }

    @Override
    public void setLevel(int level) {
        this.brightness = level;
    }

    @Override
    public int getValue() {
        return brightness;
    }

    public String getType() {
        return type;
    }
}
