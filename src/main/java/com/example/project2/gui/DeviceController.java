package com.example.project2.gui;

import com.example.project2.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/device/toggle")
    public String toggleDevice(@RequestParam String deviceId) {
        // бизнес-логика переключения устройства
        deviceService.toggleDevice(deviceId);
        System.out.println("DeviceController toggled device: " + deviceId);
        return "redirect:/devices";
    }
}
