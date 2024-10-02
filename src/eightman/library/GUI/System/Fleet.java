package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private String name;
    private final List<TaskForce> taskForces = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskForce> getTaskForces() {
        return taskForces;
    }

    public void addTaskForce(TaskForce taskForce) {
        this.taskForces.add(taskForce);
    }

    public void setNavalBase(int i) {

    }

    public void addShip(Ship ship) {
        taskForces.get(taskForces.size() - 1).addShip(ship);
    }
}