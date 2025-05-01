package core;

import java.util.*;
import devices.Device;


public class Home {
    private String name;
    private Map<String, List<Device>> rooms = new HashMap<>();

    public Home(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRoom(String roomName) {
        rooms.putIfAbsent(roomName, new ArrayList<>());
    }

    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }

    public void addDeviceToRoom(String roomName, Device device) {
        rooms.computeIfAbsent(roomName, k -> new ArrayList<>()).add(device);
    }

    public void removeDeviceFromRoom(String roomName, Device device) {
        List<Device> devices = rooms.get(roomName);
        if (devices != null) {
            devices.remove(device);
        }
    }

    public List<Device> getDevicesInRoom(String roomName) {
        return rooms.getOrDefault(roomName, new ArrayList<>());
    }

    public Map<String, List<Device>> getAllRooms() {
        return rooms;
    }
}
