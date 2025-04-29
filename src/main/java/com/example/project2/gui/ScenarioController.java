package com.example.project2.gui;

import com.example.project2.services.ScenarioService;
import core.Scenario;
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
        return "redirect:/";
    }
}
