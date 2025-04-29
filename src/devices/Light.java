package devices;

public class Light extends Device implements Switchable {
    private int brightness; // от 0 до 100

    public Light(String id, String name) {
        super(id, name);
        this.brightness = 100;
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

    public void setBrightness(int value) {
        brightness = Math.max(0, Math.min(100, value));
        System.out.println("Brightness of " + name + " set to " + brightness + "%");
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public String getStatus() {
        return "Light " + name + " is " + (isOn ? "ON" : "OFF") + ", Brightness: " + brightness + "%";
    }
}
