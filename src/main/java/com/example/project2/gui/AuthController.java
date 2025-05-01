package com.example.project2.gui;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    private static final String USERS_FILE = "users.txt";

    private Map<String, String> loadUsers() {
        Map<String, String> users = new HashMap<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        Map<String, String> users = loadUsers();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            session.setAttribute("username", username);

            File addressFile = new File("address_" + username + ".txt");
            if (addressFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(addressFile))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 4 && !line.trim().isEmpty()) {
                            String addressKey = (parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3])
                                    .replaceAll("\\s+", "");
                            session.setAttribute("addressKey", addressKey);
                            return "redirect:/home";
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Если файл пустой или адрес некорректен — идём на enter-address
            return "redirect:/enter-address";
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        Map<String, String> users = loadUsers();
        if (users.containsKey(username)) {
            model.addAttribute("error", "Пользователь уже существует");
            return "register";
        }
        saveUser(username, password);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Удаление всех данных из сессии
        return "redirect:/login";
    }

}
