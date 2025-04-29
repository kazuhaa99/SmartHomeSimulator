package devices;

public class Thermostat extends Device {

    private String room;
    private String type;

    public Thermostat(String id, String name, String room, String type) {
        super(id, name);
        this.room = room;
        this.type = type;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Термостат " + getName() + " включен в комнате " + room);
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Термостат " + getName() + " выключен в комнате " + room);
    }

    @Override
    public void performFunction() {
        if (isOn()) {
            System.out.println("Термостат " + getName() + " регулирует температуру в " + room);
        } else {
            System.out.println("Термостат " + getName() + " выключен и не функционирует.");
        }
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }
}
