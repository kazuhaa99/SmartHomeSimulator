package devices;

public class HumiditySensor extends Device implements Adjustable {
    private int humidity;

    public HumiditySensor(String id, String name, String room, String type) {
        super(id, name, room);
        this.humidity = 50; // значение по умолчанию
    }

    @Override
    public void turnOn() {
        this.on = true;
    }

    @Override
    public void turnOff() {
        this.on = false;
    }

    @Override
    public void performFunction() {
        System.out.println("Контроль влажности в " + room + ": " + humidity + "%");
    }

    @Override
    public void setLevel(int value) {
        this.humidity = value;
    }

    @Override
    public int getValue() {
        return humidity;
    }
}
