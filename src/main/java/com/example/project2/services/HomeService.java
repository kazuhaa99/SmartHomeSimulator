package com.example.project2.services;

import core.Home;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    private final List<Home> homes = new ArrayList<>();

    public void addHome(Home home) {
        homes.add(home);
    }

    public void removeHome(Home home) {
        homes.remove(home);
    }

    public List<Home> getAllHomes() {
        return new ArrayList<>(homes);
    }

    public Home getHomeByName(String name) {
        return homes.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
