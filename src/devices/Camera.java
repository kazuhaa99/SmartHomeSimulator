package devices;

public class Camera extends Device implements Switchable {
    private boolean recording;

    public Camera(String id, String name) {
        super(id, name);
        this.recording = false;
    }

    public void startRecording() {
        recording = true;
    }

    public void stopRecording() {
        recording = false;
    }

    public boolean isRecording() {
        return recording;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " camera turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        stopRecording();
        System.out.println(name + " camera turned OFF.");
    }

    @Override
    public String getStatus() {
        return "Camera " + name + " is " + (isOn ? "ON" : "OFF") +
                ", Recording: " + (recording ? "Yes" : "No");
    }
}
