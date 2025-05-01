package com.example.project2.core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import core.Action;

public class Scenario {

    private String name;
    private List<Action> actions;
    private LocalTime time;

    public Scenario(String name, List<Action> actions, LocalTime time) {
        this.name = name;
        this.actions = actions;
        this.time = time;
    }

    public Scenario(String name) {
        this.name = name;
        this.actions = new ArrayList<>();
        this.time = LocalTime.now();
    }

    public void addAction(Action action) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.add(action);
    }

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void run() {
        if (actions != null) {
            for (Action action : actions) {
                action.execute();
            }
        }
    }


    @Override
    public String toString() {
        return "Scenario{" +
                "name='" + name + '\'' +
                ", actions=" + actions +
                ", time=" + time +
                '}';
    }
}