package devices;

public class HeatedFloor extends Device {

    private String type;

    public HeatedFloor(String id, String name, String room, String type) {
        super(id, name, room); // исправленный вызов конструктора родителя
        this.type = type;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Тёплый пол " + getName() + " включен в " + getRoom());
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Тёплый пол " + getName() + " выключен в " + getRoom());
    }

    @Override
    public void performFunction() {
        if (isOn()) {
            System.out.println("Тёплый пол " + getName() + " обогревает " + getRoom());
        } else {
            System.out.println("Тёплый пол " + getName() + " выключен и не работает.");
        }
    }

    public String getType() {
        return type;
    }
}
