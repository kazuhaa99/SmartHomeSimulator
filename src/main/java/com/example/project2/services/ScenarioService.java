package com.example.project2.services;

import core.Scenario;
import devices.Device;
import devices.Light;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioService {
    private List<Scenario> scenarios = new ArrayList<>();

    public ScenarioService(DeviceService deviceService) {
        // Пример сценария
        Scenario nightMode = new Scenario("Ночной режим");
        for (Device d : deviceService.getAllDevices()) {
            if (d instanceof Light) {
                nightMode.addAction(d);
            }
        }
        scenarios.add(nightMode);
    }

    public List<Scenario> getAllScenarios() {
        return scenarios;
    }

    public void runScenario(String name) {
        for (Scenario scenario : scenarios) {
            if (scenario.getName().equals(name)) {
                scenario.run();
                break;
            }
        }
    }
}
