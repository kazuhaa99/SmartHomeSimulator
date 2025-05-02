package com.example.project2.automation;

import core.AutomationTask;
import com.example.project2.services.AutomationService;
import com.example.project2.services.DeviceService;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.time.LocalTime;
import java.util.List;

public class AutomationScheduler extends Thread {
    private final AutomationService automationService;
    private final DeviceService deviceService;
    private final String username;
    private final String address;

    public AutomationScheduler(AutomationService automationService, DeviceService deviceService,
                               String username, String address) {
        this.automationService = automationService;
        this.deviceService = deviceService;
        this.username = username;
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println("✅ Автоматизация запущена для пользователя: " + username);
        while (!isInterrupted()) {
            automationService.loadTasks(); // 🔁 Обновляем задачи из файла каждый раз
            checkAndExecuteTasks();       // ⏰ Проверка
            try {
                Thread.sleep(60000);      // ⏳ Раз в минуту
            } catch (InterruptedException e) {
                System.out.println("⛔ Автоматизация остановлена для: " + username);
                break;
            }
        }
    }


    private void checkAndExecuteTasks() {
        Map<String, List<AutomationTask>> roomTasks = automationService.getTasksForUser(username, address);
        String currentTime = LocalTime.now().withSecond(0).withNano(0).toString();

        System.out.println("Текущее время: " + currentTime);

        for (Map.Entry<String, List<AutomationTask>> entry : roomTasks.entrySet()) {
            String room = entry.getKey();
            List<AutomationTask> taskList = entry.getValue();

            taskList.removeIf(task -> {
                if (task.getTime().toString().equals(currentTime)) {
                    deviceService.executeAutomationTask(task);
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String message = "✅ Выполнено: " + task.getDeviceName() + " в " + task.getRoomName() + " (" + time + ")";
                    automationService.addNotification(username, address, message);
                    System.out.println("✅ Выполнено: " + task);
                    return true; // Удаляем задачу после выполнения
                }
                return false;
            });
        }

        automationService.saveTasks(); // 💾 Сохраняем после удаления выполненных
    }


}
