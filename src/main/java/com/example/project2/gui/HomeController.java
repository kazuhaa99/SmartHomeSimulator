package com.example.project2.gui;

import core.Home;
import com.example.project2.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homes")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public List<Home> getAllHomes() {
        return homeService.getAllHomes();
    }

    @GetMapping("/{name}")
    public Home getHomeByName(@PathVariable String name) {
        return homeService.getHomeByName(name);
    }

    @PostMapping
    public void addHome(@RequestBody Home home) {
        homeService.addHome(home);
    }

    @DeleteMapping("/{name}")
    public void deleteHome(@PathVariable String name) {
        Home home = homeService.getHomeByName(name);
        if (home != null) {
            homeService.removeHome(home);
        }
    }
}
