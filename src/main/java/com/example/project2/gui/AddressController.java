package com.example.project2.gui;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class AddressController {

    @GetMapping("/enter-address")
    public String showAddressForm(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        return "enter-address";
    }

    @PostMapping("/enter-address")
    public String handleAddressSubmit(@RequestParam String city,
                                      @RequestParam String street,
                                      @RequestParam String building,
                                      @RequestParam String apartment,
                                      HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("address_" + username + ".txt"))) {
            writer.write(city + "," + street + "," + building + "," + apartment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String addressKey = (city + "_" + street + "_" + building + "_" + apartment).replaceAll("\\s+", "");
        session.setAttribute("addressKey", addressKey);

        return "redirect:/home";
    }
}
