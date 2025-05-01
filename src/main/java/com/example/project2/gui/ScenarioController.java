package com.example.project2.gui;

import com.example.project2.services.ScenarioService;
import com.example.project2.core.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scenarios")
public class ScenarioController {


    @Autowired
    private ScenarioService scenarioService;

    @GetMapping
    public String listScenarios(Model model) {
        model.addAttribute("scenarios", scenarioService.getAllScenarios());
        return "scenarios";
    }

    @PostMapping("/run")
    public String runScenario(@RequestParam String name) {
        scenarioService.runScenario(name);
        return "redirect:/scenarios";
    }

    @PostMapping("/delete")
    public String deleteScenario(@RequestParam String name) {
        scenarioService.deleteScenario(name);
        return "redirect:/scenarios";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("scenario", new Scenario(""));
        return "create-scenario";
    }

    @PostMapping("/create")
    public String createScenario(@ModelAttribute Scenario scenario) {
        scenarioService.addScenario(scenario);
        return "redirect:/scenarios";
    }

    @GetMapping("/{name}")
    public String viewScenario(@PathVariable String name, Model model) {
        Scenario scenario = scenarioService.findByName(name);
        model.addAttribute("scenario", scenario);
        model.addAttribute("devices", scenarioService.getAvailableDevices());
        return "scenario-details";
    }

    @PostMapping("/{name}/add-device")
    public String addDeviceToScenario(@PathVariable String name, @RequestParam String deviceId) {
        scenarioService.addDeviceToScenario(name, deviceId);
        return "redirect:/scenarios/" + name;
    }
}
