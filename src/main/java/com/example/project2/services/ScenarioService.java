package com.example.project2.services;

import core.Action;
import core.DeviceAction;
import com.example.project2.core.Scenario;
import devices.Device;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioService {
    private final List<Scenario> scenarios = new ArrayList<>();
    private final DeviceService deviceService;

    public ScenarioService(DeviceService deviceService) {
        this.deviceService = deviceService;

        // Пример начального сценария
        Scenario nightMode = new Scenario("Ночной режим");
        for (Device d : deviceService.getAllDevices()) {
            if (d.getName().toLowerCase().contains("light")) {
                nightMode.addAction(new DeviceAction(d)); // Обертываем в Action
            }
        }
        scenarios.add(nightMode);
    }

    public List<Scenario> getAllScenarios() {
        return scenarios;
    }

    public void addScenario(Scenario scenario) {
        scenarios.add(scenario);
    }

    public void deleteScenario(String name) {
        scenarios.removeIf(s -> s.getName().equals(name));
    }

    public Scenario findByName(String name) {
        return scenarios.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void addDeviceToScenario(String scenarioName, String deviceId) {
        Scenario scenario = findByName(scenarioName);
        Device device = deviceService.getDeviceById(deviceId);
        if (scenario != null && device != null) {
            scenario.addAction(new DeviceAction(device)); // Обертываем в Action
        }
    }

    public void runScenario(String name) {
        Scenario scenario = findByName(name);
        if (scenario != null) {
            scenario.run();
        }
    }

    public List<Device> getAvailableDevices() {
        return new ArrayList<>(deviceService.getAvailableDevices()); // Преобразуем в List
    }
}
