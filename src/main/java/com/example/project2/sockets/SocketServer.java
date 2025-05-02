package com.example.project2.sockets;

import com.example.project2.services.DeviceService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {
    private final DeviceService deviceService;
    private final int port = 5566;

    public SocketServer(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🔌 Socket-сервер запущен на порту " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, deviceService).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
