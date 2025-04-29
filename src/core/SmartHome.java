package core;

import java.io.Serializable;
import java.util.*;

public class SmartHome implements Serializable {
    private List<Room> rooms;
    private Map<String, User> users;

    public SmartHome() {
        this.rooms = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void showAll() {
        for (Room room : rooms) {
            room.showDevices();
        }
    }
}
