package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario implements Serializable {
    private String name;
    private List<Action> actions = new ArrayList<>();

    public Scenario(String name) {
        this.name = name;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void run() {
        for (Action action : actions) {
            action.execute();
        }
    }

    public String getName() {
        return name;
    }

    public List<Action> getActions() {
        return actions;
    }
}
