package com.example.project2.services;

import com.example.project2.core.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String USER_FILE = "users.dat";

    private Map<String, User> users;

    public UserService() {
        users = loadUsersFromFile();
    }

    public void registerUser(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
    }

    public Optional<User> authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void saveUser(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении пользователей: " + e.getMessage());
        }
    }

    private Map<String, User> loadUsersFromFile() {
        File file = new File(USER_FILE);
        if (!file.exists()) return new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке пользователей: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
