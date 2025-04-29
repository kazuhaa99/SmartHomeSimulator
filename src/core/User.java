package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String role; // "Admin", "Guest"
    private List<String> scenarioNames;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
        this.scenarioNames = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void addScenario(String scenarioName) {
        scenarioNames.add(scenarioName);
    }

    public List<String> getScenarios() {
        return scenarioNames;
    }
}
