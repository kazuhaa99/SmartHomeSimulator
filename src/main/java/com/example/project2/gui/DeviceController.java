package com.example.project2.gui;

import com.example.project2.services.DeviceService;
import devices.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public String showDevices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        return "devices";
    }

    @PostMapping("/device/update")
    public String updateDevice(@RequestParam String deviceId, @RequestParam int value) {
        deviceService.updateDeviceValue(deviceId, value);
        return "redirect:/devices";
    }
}
