package com.example.project2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Room;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class RoomService {

    private final ObjectMapper objectMapper;

    public RoomService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public Map<String, Room> loadRoomsForAddress(String addressKey) {
        File file = getFileForAddress(addressKey);
        Map<String, Room> rooms = new HashMap<>();

        if (file.exists()) {
            try {
                rooms = objectMapper.readValue(file, new TypeReference<>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Добавляем недостающие комнаты, если их ещё нет
        if (!rooms.containsKey("Кухня")) rooms.put("Кухня", new Room("Кухня", 22.0, 70, 50.0));
        if (!rooms.containsKey("Зал")) rooms.put("Зал", new Room("Зал", 23.5, 80, 45.0));
        if (!rooms.containsKey("Коридор")) rooms.put("Коридор", new Room("Коридор", 21.0, 60, 40.0));
        if (!rooms.containsKey("Спальня")) rooms.put("Спальня", new Room("Спальня", 22.0, 50, 45.0));

        saveRoomsForAddress(addressKey, rooms); // сохраняем даже при добавлении

        return rooms;
    }


    public void saveRoomsForAddress(String addressKey, Map<String, Room> rooms) {
        File file = getFileForAddress(addressKey); // ИСПОЛЬЗУЙ ЕДИНУЮ ЛОГИКУ
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateRoomForAddress(String addressKey, Room updatedRoom) {
        Map<String, Room> rooms = loadRoomsForAddress(addressKey);
        rooms.put(updatedRoom.getName(), updatedRoom); // Обновляем только одну комнату
        saveRoomsForAddress(addressKey, rooms);
    }


    private File getFileForAddress(String addressKey) {
        return new File("rooms_" + addressKey + ".json");
    }
}
