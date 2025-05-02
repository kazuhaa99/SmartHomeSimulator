package com.example.project2.services;

import core.AutomationTask;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class AutomationService {

    private final ObjectMapper objectMapper;
    private final Map<String, Map<String, List<AutomationTask>>> userTasks = new HashMap<>();
    private final File storageFile = new File("automation_tasks.json");

    public AutomationService() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private final Map<String, List<String>> notifications = new ConcurrentHashMap<>();

    public void addNotification(String username, String address, String message) {
        String key = username + "@" + address;
        notifications.computeIfAbsent(key, k -> Collections.synchronizedList(new ArrayList<>())).add(message);
    }

    public List<String> getAndClearNotifications(String username, String address) {
        String key = username + "@" + address;
        List<String> result = notifications.remove(key);
        return result != null ? result : new ArrayList<>();
    }


    @PostConstruct
    public void loadTasks() {
        if (storageFile.exists()) {
            try {
                Map<String, Map<String, List<AutomationTask>>> loaded = objectMapper.readValue(
                        storageFile, new TypeReference<>() {}
                );
                userTasks.putAll(loaded);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<AutomationTask> getTasksForRoom(String username, String address, String room) {
        String key = username + "@" + address;
        return userTasks
                .getOrDefault(key, new HashMap<>())
                .getOrDefault(room, new ArrayList<>());
    }

    public Map<String, List<AutomationTask>> getTasksForUser(String username, String address) {
        String key = username + "@" + address;
        return userTasks.getOrDefault(key, new HashMap<>());
    }

    public void addTask(String username, String address, AutomationTask task) {
        String key = username + "@" + address;
        userTasks
                .computeIfAbsent(key, k -> new HashMap<>())
                .computeIfAbsent(task.getRoomName(), r -> new ArrayList<>())
                .add(task);
        saveTasks();
    }

    public void deleteTask(String username, String address, String room, String deviceType, int index) {
        String key = username + "@" + address;
        Map<String, List<AutomationTask>> userRoomMap = userTasks.getOrDefault(key, new HashMap<>());
        List<AutomationTask> tasks = userRoomMap.getOrDefault(room, new ArrayList<>());

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks();
        }
    }

    public void saveTasks() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(storageFile, userTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
