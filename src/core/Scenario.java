package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario implements Serializable {
    private String name;
    private List<Runnable> actions;

    public Scenario(String name) {
        this.name = name;
        this.actions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAction(Runnable action) {
        actions.add(action);
    }

    public void execute() {
        System.out.println("Executing scenario: " + name);
        for (Runnable action : actions) {
            action.run();
        }
    }
}
