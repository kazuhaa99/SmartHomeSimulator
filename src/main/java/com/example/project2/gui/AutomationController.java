package com.example.project2.gui;

import com.example.project2.services.AutomationService;
import com.example.project2.services.DeviceService;
import core.AutomationTask;
import devices.Device;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AutomationController {

    private final AutomationService automationService;
    private final DeviceService deviceService;

    public AutomationController(AutomationService automationService, DeviceService deviceService) {
        this.automationService = automationService;
        this.deviceService = deviceService;
    }

    @GetMapping("/automation")
    public String showAutomation(@RequestParam(required = false) String room,
                                 HttpSession session,
                                 Model model) {
        String username = (String) session.getAttribute("username");
        String address = (String) session.getAttribute("addressKey");


        if (username == null || address == null) {
            return "redirect:/login";
        }

        model.addAttribute("rooms", List.of("Зал", "Кухня", "Коридор","Спальня"));
        model.addAttribute("selectedRoom", room);

        if (room != null) {
            List<Device> devices = deviceService.getDevicesByRoom(room);
            List<AutomationTask> tasks = automationService.getTasksForRoom(username, address, room);

            model.addAttribute("devices", devices);
            model.addAttribute("tasks", tasks);
        }

        return "automation";
    }

    @PostMapping("/automation/add")
    public String addAutomation(@RequestParam String room,
                                @RequestParam String deviceId,
                                @RequestParam String turnOn,
                                @RequestParam String time,
                                HttpSession session) {

        String username = (String) session.getAttribute("username");
        String address = (String) session.getAttribute("addressKey");

        boolean shouldTurnOn = Boolean.parseBoolean(turnOn);
        Device device = deviceService.getDeviceById(deviceId);

        if (device != null && username != null && address != null) {
            AutomationTask task = new AutomationTask(
                    room,
                    device.getDeviceType(), // <--- исправлено
                    device.getName(),       // <--- исправлено
                    shouldTurnOn,
                    LocalTime.parse(time)
            );
            automationService.addTask(username, address, task);
        }

        return "redirect:/automation?room=" + URLEncoder.encode(room, StandardCharsets.UTF_8); // <--- исправлено
    }


    @PostMapping("/automation/delete")
    public String deleteAutomationTask(@RequestParam String roomName,
                                       @RequestParam String deviceType,
                                       @RequestParam int taskId,
                                       HttpSession session) {
        String username = (String) session.getAttribute("username");
        String address = (String) session.getAttribute("addressKey");

        if (username != null && address != null) {
            automationService.deleteTask(username, address, roomName, deviceType, taskId);
        }

        // Кодируем имя комнаты в UTF-8
        String encodedRoom = URLEncoder.encode(roomName, StandardCharsets.UTF_8);
        return "redirect:/automation?room=" + encodedRoom;
    }

}
