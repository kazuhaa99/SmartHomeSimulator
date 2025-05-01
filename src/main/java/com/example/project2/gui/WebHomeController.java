package com.example.project2.gui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHomeController {

    @GetMapping("/dashboard")
    public String showHomePage(Model model) {
        model.addAttribute("message", "Добро пожаловать в умный дом!");
        return "dashboard";
    }
}
