package com.example.project2.sockets;

import com.example.project2.services.DeviceService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SocketInitializer {

    private final DeviceService deviceService;

    public SocketInitializer(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostConstruct
    public void startSocketServer() {
        SocketServer socketServer = new SocketServer(deviceService);
        socketServer.start();
    }
}
