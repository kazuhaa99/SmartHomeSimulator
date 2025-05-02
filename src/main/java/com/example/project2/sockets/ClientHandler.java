package com.example.project2.sockets;

import com.example.project2.services.DeviceService;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final DeviceService deviceService;

    public ClientHandler(Socket socket, DeviceService deviceService) {
        this.socket = socket;
        this.deviceService = deviceService;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                String[] parts = input.split(":");
                if (parts.length == 3 && parts[0].equalsIgnoreCase("SET")) {
                    String deviceId = parts[1];
                    boolean state = parts[2].equalsIgnoreCase("ON");
                    deviceService.setDeviceState(deviceId, state);
                    out.println("✅ " + deviceId + " => " + (state ? "Включено" : "Выключено"));
                } else {
                    out.println("❌ Неверная команда");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
