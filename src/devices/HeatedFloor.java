package devices;

public class HeatedFloor extends Device {

    private String room;
    private String type;

    public HeatedFloor(String id, String name, String room, String type) {
        super(id, name);
        this.room = room;
        this.type = type;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Тёплый пол " + getName() + " включен в " + room);
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Тёплый пол " + getName() + " выключен в " + room);
    }

    @Override
    public void performFunction() {
        if (isOn()) {
            System.out.println("Тёплый пол " + getName() + " обогревает " + room);
        } else {
            System.out.println("Тёплый пол " + getName() + " выключен и не работает.");
        }
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }
}
