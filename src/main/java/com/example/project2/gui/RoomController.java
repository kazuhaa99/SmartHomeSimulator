package com.example.project2.gui;

import com.example.project2.services.RoomService;
import core.Room;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/room")
    public String getRoom(@RequestParam String name, HttpSession session, Model model) {
        String addressKey = (String) session.getAttribute("addressKey");
        if (addressKey == null) {
            return "redirect:/enter-address";
        }

        Map<String, Room> rooms = roomService.loadRoomsForAddress(addressKey);
        Room room = rooms.get(name);
        if (room == null) {
            model.addAttribute("error", "Комната с именем '" + name + "' не найдена.");
            return "error";
        }

        model.addAttribute("room", room);
        return "room";
    }

    @PostMapping("/room/update")
    public String updateRoom(@ModelAttribute Room room, HttpSession session, Model model) {
        String addressKey = (String) session.getAttribute("addressKey");
        if (addressKey != null) {
            roomService.updateRoomForAddress(addressKey, room);
            model.addAttribute("room", room); // НЕ подгружай заново
        }
        return "room";
    }
}
