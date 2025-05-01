package core;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;

public class AutomationTask {
    private String roomName;
    private String deviceType;
    private String deviceName;
    private boolean turnOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") // Добавлено
    private LocalTime time;

    public AutomationTask() {}

    public AutomationTask(String roomName, String deviceType, String deviceName, boolean turnOn, LocalTime time) {
        this.roomName = roomName;
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.turnOn = turnOn;
        this.time = time;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isTurnOn() {
        return turnOn;
    }

    public void setTurnOn(boolean turnOn) {
        this.turnOn = turnOn;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
